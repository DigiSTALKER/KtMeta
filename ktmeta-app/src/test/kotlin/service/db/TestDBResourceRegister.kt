package service.db

import io.github.hochikong.ktmeta.dao.DBResourceRecord
import io.github.hochikong.ktmeta.dao.impl.DBResourceRegister
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDBResourceRegister {
    companion object {

        @JvmStatic
        @BeforeAll
        fun doBefore() {
            DBResourceRegister.drop()
        }

        @JvmStatic
        @AfterAll
        fun doAfter() {
            DBResourceRegister.drop()
        }
    }

    val t1 = DBResourceRecord(
        db_type = "Sqlite",
        db_name = "my sqlite",
        db_desc = "sqlite desc",
        db_url = "url1",
        user = "",
        password = "",
        save_passwd = 0
    )

    val t2 = DBResourceRecord(
        db_type = "Sqlite",
        db_name = "my sqlite new",
        db_desc = "sqlite desc",
        db_url = "url1",
        user = "",
        password = "",
        save_passwd = 0
    )

    @Test
    @Order(1)
    fun all() {
        // no table first
        assertEquals(false, DBResourceRegister.hasTable())

        // create it
        assertEquals(true, DBResourceRegister.resetTable())

        // has table now
        assertEquals(true, DBResourceRegister.hasTable())

        // insert
        assertEquals(true, DBResourceRegister.insertRecord(t1))

        // query
        val rs = DBResourceRegister.getAllRecords()
        assertEquals(rs[0].db_type, t1.db_type)
        assertEquals(rs[0].db_desc, t1.db_desc)
        println("Query: ${rs[0]}")

        // update
        DBResourceRegister.updateRecord(rs[0].id, t2)
        val rs1 = DBResourceRegister.getAllRecords()
        assertEquals(rs1[0].db_name, t2.db_name)
        println("Update ${rs1[0]}")

        // delete
        assertEquals(true, DBResourceRegister.deleteRecord(rs1[0].id))
        println("After deleted: ${DBResourceRegister.getAllRecords()}")

    }
}