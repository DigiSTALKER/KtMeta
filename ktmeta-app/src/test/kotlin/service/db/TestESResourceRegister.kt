package service.db

import io.github.hochikong.ktmeta.dao.impl.ESResourceRegister
import io.github.hochikong.ktmeta.dao.ESResourceRecord
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestESResourceRegister {
    companion object {

        @JvmStatic
        @BeforeAll
        fun doBefore() {
            ESResourceRegister.drop()
        }

        @JvmStatic
        @AfterAll
        fun doAfter() {
            ESResourceRegister.drop()
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
        assertEquals(false, ESResourceRegister.hasTable())

        // create it
        assertEquals(true, ESResourceRegister.resetTable())

        // has table now
        assertEquals(true, ESResourceRegister.hasTable())

        // insert
        assertEquals(true, ESResourceRegister.insertRecord(t1))

        // query
        val rs = ESResourceRegister.getAllRecords()
        assertEquals(rs[0].index_name, t1.index_name)
        assertEquals(rs[0].index_url, t1.index_url)
        println("Query: ${rs[0]}")

        // update
        ESResourceRegister.updateRecord(rs[0].id, t2)
        val rs1 = ESResourceRegister.getAllRecords()
        assertEquals(rs1[0].index_name, t2.index_name)
        println("Update ${rs1[0]}")

        // delete
        assertEquals(true, ESResourceRegister.deleteRecord(rs1[0].id))
        println("After deleted: ${ESResourceRegister.getAllRecords()}")

    }
}