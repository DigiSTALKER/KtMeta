package service.db

import io.github.hochikong.ktmeta.dao.impl.ESResourceDAO
import io.github.hochikong.ktmeta.dao.ESResourceRecord
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestESResourceDAO {
    companion object {

        @JvmStatic
        @BeforeAll
        fun doBefore() {
            ESResourceDAO.drop()
        }

        @JvmStatic
        @AfterAll
        fun doAfter() {
            ESResourceDAO.drop()
        }
    }

    val dao = ESResourceDAO
    val t1 = ESResourceRecord(
        index_name = "index 1",
        index_desc = "1 desc",
        index_url = "1 url"
    )

    val t2 = ESResourceRecord(
        index_name = "index 1 new",
        index_desc = "1 desc",
        index_url = "1 url"
    )

    @Test
    @Order(1)
    fun all() {
        // no table first
        assertEquals(false, ESResourceDAO.hasTable())

        // create it
        assertEquals(true, ESResourceDAO.resetTable())

        // has table now
        assertEquals(true, ESResourceDAO.hasTable())

        // insert
        assertEquals(true, ESResourceDAO.insertRecord(t1))

        // query
        val rs = ESResourceDAO.getAllRecords()
        assertEquals(rs[0].index_name, t1.index_name)
        assertEquals(rs[0].index_url, t1.index_url)
        println("Query: ${rs[0]}")

        // update
        ESResourceDAO.updateRecord(rs[0].id, t2)
        val rs1 = ESResourceDAO.getAllRecords()
        assertEquals(rs1[0].index_name, t2.index_name)
        println("Update ${rs1[0]}")

        // delete
        assertEquals(true, ESResourceDAO.deleteRecord(rs1[0].id))
        println("After deleted: ${ESResourceDAO.getAllRecords()}")

    }
}