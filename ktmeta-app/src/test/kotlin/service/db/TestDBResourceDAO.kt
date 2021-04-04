package service.db

import io.github.hochikong.ktmeta.dao.DBResource
import io.github.hochikong.ktmeta.dao.impl.DBResourceDAO
import org.junit.jupiter.api.*
import org.jdbi.v3.core.statement.UnableToCreateStatementException
import org.junit.jupiter.api.Assertions.assertEquals


@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDBResourceDAO {
    companion object {

        @JvmStatic
        @BeforeAll
        fun doBefore() {
            DBResourceDAO.drop()
        }

        @JvmStatic
        @AfterAll
        fun doAfter() {
            DBResourceDAO.drop()
        }
    }

    val dao = DBResourceDAO
    val t1 = DBResource(
        db_type = "Sqlite",
        db_name = "my sqlite",
        db_desc = "sqlite desc",
        db_url = "url1",
        user = "",
        password = "",
        save_passwd = 0
    )

    val t2 = DBResource(
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
        assertEquals(false, DBResourceDAO.hasTable())

        // create it
        assertEquals(true, DBResourceDAO.resetTable())

        // has table now
        assertEquals(true, DBResourceDAO.hasTable())

        // insert
        assertEquals(true, DBResourceDAO.insertRecord(t1))

        // query
        val rs = DBResourceDAO.getAllRecords()
        assertEquals(rs[0].db_type, t1.db_type)
        assertEquals(rs[0].db_desc, t1.db_desc)
        println("Query: ${rs[0]}")

        // update
        DBResourceDAO.updateRecord(rs[0].id, t2)
        val rs1 = DBResourceDAO.getAllRecords()
        assertEquals(rs1[0].db_name, t2.db_name)
        println("Update ${rs1[0]}")

        // delete
        assertEquals(true, DBResourceDAO.deleteRecord(rs1[0].id))
        println("After deleted: ${DBResourceDAO.getAllRecords()}")

    }
}