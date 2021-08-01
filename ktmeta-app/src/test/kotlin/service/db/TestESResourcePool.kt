package service.db

import io.github.hochikong.ktmeta.dao.impl.ESResourcePool
import io.github.hochikong.ktmeta.dao.ESResourceRecord
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestESResourcePool {
    companion object {

        @JvmStatic
        @BeforeAll
        fun doBefore() {
            ESResourcePool.drop()
        }

        @JvmStatic
        @AfterAll
        fun doAfter() {
            ESResourcePool.drop()
        }
    }

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
        assertEquals(false, ESResourcePool.hasTable())

        // create it
        assertEquals(true, ESResourcePool.resetTable())

        // has table now
        assertEquals(true, ESResourcePool.hasTable())

        // insert
        assertEquals(true, ESResourcePool.insertRecord(t1))

        // query
        val rs = ESResourcePool.getAllRecords()
        assertEquals(rs[0].index_name, t1.index_name)
        assertEquals(rs[0].index_url, t1.index_url)
        println("Query: ${rs[0]}")

        // update
        ESResourcePool.updateRecord(rs[0].id, t2)
        val rs1 = ESResourcePool.getAllRecords()
        assertEquals(rs1[0].index_name, t2.index_name)
        println("Update ${rs1[0]}")

        // delete
        assertEquals(true, ESResourcePool.deleteRecord(rs1[0].id))
        println("After deleted: ${ESResourcePool.getAllRecords()}")

    }
}