/*
 * Copyright 2020 Hochikong
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package service.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.hochikong.ktmeta.dao.DBRecord
import io.github.hochikong.ktmeta.dao.impl.DBResourceDAO
import io.github.hochikong.ktmeta.predefined.Encryption
import io.github.hochikong.ktmeta.service.db.DBProvider
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.File

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDBProvider {
    companion object {
        private val f = File("testCache/mytestdb.db")

        @JvmStatic
        @AfterAll
        fun doAfter() {
            DBResourceDAO.resetTable()

            val config = HikariConfig()
            config.maximumPoolSize = 1
            config.jdbcUrl = "jdbc:postgresql://localhost:5432/ktmetapg"
            config.username = "ktmeta_test"
            config.password = "ktmeta"
            val nds = HikariDataSource(config)
            nds.connection.use { conn ->
                conn.createStatement().use {
                    it.execute("DROP TABLE sb;")
                }
            }

            val config1 = HikariConfig()
            config1.maximumPoolSize = 1
            config1.jdbcUrl = "jdbc:sqlite:testCache/mytestdb.db"
            val nds1 = HikariDataSource(config1)
            nds1.connection.use { conn ->
                conn.createStatement().use {
                    it.execute("DROP TABLE sb;")
                }
            }
        }

        @JvmStatic
        @BeforeAll
        fun doBefore() {
            DBResourceDAO.resetTable()
        }
    }

    @Order(1)
    @Test
    fun testGetDB() {
        DBResourceDAO.insertRecord(
            DBRecord(
                -1,
                "Sqlite",
                "mydb",
                "mydb desc",
                "jdbc:sqlite:testCache/mytestdb.db",
                "null",
                "null",
                0
            )
        )
        DBResourceDAO.insertRecord(
            DBRecord(
                -1,
                "Postgresql",
                "mydb2",
                "mydb2 desc",
                "jdbc:postgresql://localhost:5432/ktmetapg",
                Encryption.encrypt("ktmeta_test"),
                Encryption.encrypt("ktmeta"),
                1
            )
        )

        val db = DBProvider.getDatabase("mydb")!!
        db.useConnection { conn ->
            val sql = """
            create table sb(id integer primary key, name varchar not null);
        """.trimIndent()
            conn.createStatement().use { it.execute(sql) }
            conn.createStatement().use {
                it.execute(
                    """
            INSERT INTO sb values(1, 'sb');
        """.trimIndent()
                )
            }
        }

        val tmp = mutableMapOf<Int, String>()
        db.useConnection { conn ->
            val sql = """
                select * from sb;
            """.trimIndent()
            conn.createStatement().use {
                val r = it.executeQuery(sql)
                while (r.next()) {
                    val id = r.getInt(1)
                    val name = r.getString(2)
                    println("id is $id, name is $name")
                    tmp[id] = name
                }
                r.close()
            }
        }
        assertEquals(mutableMapOf(1 to "sb"), tmp)
    }

    @Order(2)
    @Test
    fun testGetConn() {
        val ds = DBProvider.getDataSource("mydb2", "ktmeta_test", "ktmeta")!!
        ds.connection.use { conn ->
            val sql = """
            create table sb(id integer primary key, name varchar not null);
            """.trimIndent()
            conn.createStatement().use { it.execute(sql) }
            conn.createStatement().use {
                val r = it.executeQuery(
                    """
                select 1 from sb;
            """.trimIndent()
                )
                while (r.next()) {
                    val value = r.getInt(0)
                    assertEquals(1, value)
                }
                r.close()
            }
        }
    }
}