package service.db

import io.github.hochikong.ktmeta.dao.DBResourceRecord
import io.github.hochikong.ktmeta.dao.impl.DBResourcePool
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDBResourcePool {
    companion object {

        @JvmStatic
        @BeforeAll
        fun doBefore() {
            DBResourcePool.drop()
        }

        @JvmStatic
        @AfterAll
        fun doAfter() {
            DBResourcePool.drop()
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
        assertEquals(false, DBResourcePool.hasTable())

        // create it
        assertEquals(true, DBResourcePool.resetTable())

        // has table now
        assertEquals(true, DBResourcePool.hasTable())

        // insert
        assertEquals(true, DBResourcePool.insertRecord(t1))

        // query
        val rs = DBResourcePool.getAllRecords()
        assertEquals(rs[0].db_type, t1.db_type)
        assertEquals(rs[0].db_desc, t1.db_desc)
        println("Query: ${rs[0]}")

        // update
        DBResourcePool.updateRecord(rs[0].id, t2)
        val rs1 = DBResourcePool.getAllRecords()
        assertEquals(rs1[0].db_name, t2.db_name)
        println("Update ${rs1[0]}")

        // delete
        assertEquals(true, DBResourcePool.deleteRecord(rs1[0].id))
        println("After deleted: ${DBResourcePool.getAllRecords()}")

    }

    @Test
    @Order(2)
    fun getDBRecord() {
        println("Doing getDBRecord \n")
        DBResourcePool.insertRecord(t2)

        val r = DBResourcePool.getRecordByName("sb")
        assertEquals(-1, r.id)
        println("r is $r")

        val r1 = DBResourcePool.getRecordByName("my sqlite new")
        assertEquals("sqlite desc", r1.db_desc)
        println("r1 is $r1")
    }
}