package service.db

import io.github.hochikong.ktmeta.common.DatabaseNotFoundException
import io.github.hochikong.ktmeta.common.Encryption
import io.github.hochikong.ktmeta.dao.DBResourceRecord
import io.github.hochikong.ktmeta.dao.impl.DBResourcePool
import io.github.hochikong.ktmeta.service.db.DBProvider
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDBProvider {
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

    val rawPasswd = "dbuserpass"

    @Test
    @Order(1)
    fun prepare() {
        DBResourcePool.resetTable()
        assertEquals(
            true, DBResourcePool.insertRecord(
                DBResourceRecord(
                    db_type = "Postgresql",
                    db_name = "Debian Test",
                    db_desc = "Test postgresql on debian",
                    db_url = "jdbc:postgresql://192.168.142.130:5432/testdb",
                    user = "dbuser",
                    password = Encryption.encrypt(rawPasswd),
                    save_passwd = 1
                )
            )
        )
    }

    @Test
    @Order(2)
    fun getDS() {
        assertThrows<DatabaseNotFoundException> {
            DBProvider.getDataSource(
                dbName = "asd",
                user = "dbuser",
                password = rawPasswd
            )
        }

        val ds = DBProvider.getDataSource(dbName = "Debian Test", user = "dbuser", password = rawPasswd)
        ds.connection.use { conn ->
            conn.createStatement().use {
                it.execute("SELECT 1 AS x FROM drinks;")
                val rs = it.resultSet

                println("Print result set: ")
                while (rs.next()) {
                    println(rs.getInt("x"))
                }
            }

        }
    }

}