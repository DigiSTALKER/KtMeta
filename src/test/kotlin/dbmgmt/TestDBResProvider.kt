/*
 * Copyright 2020 Hochikong
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package dbmgmt
/*
 * Copyright 2020 Hochikong
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import io.github.hochikong.ktmeta.dbmgmt.DBMaintainer
import io.github.hochikong.ktmeta.dbmgmt.DBRegCatalog
import io.github.hochikong.ktmeta.dbmgmt.DBResourcesProvider
import io.github.hochikong.ktmeta.predefined.Encryption
import io.github.hochikong.ktmeta.predefined.NoDatabasesIsAvailable
import io.github.hochikong.ktmeta.predefined.NoSuchDatabaseInRegistrationTable
import io.github.hochikong.ktmeta.predefined.SupportedDBs
import me.liuwj.ktorm.dsl.from
import me.liuwj.ktorm.dsl.insertAndGenerateKey
import me.liuwj.ktorm.dsl.select
import me.liuwj.ktorm.schema.Table
import me.liuwj.ktorm.schema.int
import me.liuwj.ktorm.schema.varchar
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import java.sql.DriverManager

object Demo : Table<Nothing>("demo") {
    val id = int("id").primaryKey()
    val name = varchar("name")
}

object PG : Table<Nothing>("sb") {
    val id = int("id").primaryKey()
    val name = varchar("name")
}


@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDBMGMT {
    companion object {
        @JvmStatic
        @BeforeAll
        fun bA() {
            Class.forName("org.postgresql.Driver")
            val x =
                DriverManager.getConnection("jdbc:postgresql://localhost:5432/ktmetapg?user=ktmeta_test&password=ktmeta")
            x.createStatement().use {
                it.executeUpdate(
                    """
                    CREATE TABLE sb(
                        id BIGSERIAL PRIMARY KEY ,
                        name VARCHAR(20) NOT NULL UNIQUE
                    );
                """.trimIndent()
                )
            }
        }

        @JvmStatic
        @AfterAll
        fun aA() {
            DBMaintainer.dropTable()
            Class.forName("org.postgresql.Driver")
            val x =
                DriverManager.getConnection("jdbc:postgresql://localhost:5432/ktmetapg?user=ktmeta_test&password=ktmeta")
            x.createStatement().use {
                it.executeUpdate(
                    """
                    DROP TABLE sb;
                """.trimIndent()
                )
            }
            x.close()
            Class.forName("org.sqlite.JDBC")
            val y = DriverManager.getConnection("jdbc:sqlite:d1.db")
            y.createStatement().use {
                it.executeUpdate(
                    """
                    DROP TABLE demo;
                """.trimIndent()
                )
            }
            y.close()
        }
    }

    @Order(1)
    @Test
    fun testquery() {
        DBResourcesProvider.queryReg()
        assertEquals(true, DBResourcesProvider.regIsEmpty)
        println(DBResourcesProvider.checkCatalog())
        assertThrows<NoDatabasesIsAvailable> { DBResourcesProvider.getConnection("sb", "fake") }
    }

    @Order(2)
    @Test
    fun testInsert() {
        val x = DBResourcesProvider.addDatabase(
            SupportedDBs.SQLite,
            "MY SQLITE",
            "null",
            "null",
            "sqlite_test",
            "the first sqlite db",
            "jdbc:sqlite:d1.db"
        )
        assertEquals(true, x)
        assertEquals(false, DBResourcesProvider.regIsEmpty)
        DBResourcesProvider.addDatabase(
            SupportedDBs.PostgreSQL,
            "MY PG",
            Encryption.encrypt("ktmeta_test"),
            Encryption.encrypt("ktmeta"),
            "pg_test",
            "the first pg",
            "jdbc:postgresql://localhost:5432/ktmetapg"
        )
        println("After insert: ${DBResourcesProvider.checkCatalog()}")
    }

    @Order(3)
    @Test
    fun testGetCon() {
        assertThrows<NoSuchDatabaseInRegistrationTable> { DBResourcesProvider.getConnection("sb", "fake") }
        assertThrows<NoSuchDatabaseInRegistrationTable> { DBResourcesProvider.getDatabase("sb", "fake") }
        assertThrows<NoSuchDatabaseInRegistrationTable> { DBResourcesProvider.getPool("sb", "fake") }
    }

    @Order(4)
    @Test
    fun testGetCon2() {
        println("Current catalog: ${DBResourcesProvider.checkCatalog()}")
        val sqliteToken = DBResourcesProvider.grantDatabase("sqlite_test", "null", "null")
        val x = DBResourcesProvider.getConnection("sqlite_test", sqliteToken)
        x.createStatement().use {
            it.execute(
                """
                create table demo(
                    id int primary key,
                    name text not null
                    );
            """.trimIndent()
            )
        }
        x.createStatement().use {
            it.executeUpdate(
                """
                insert into demo(id, name)
                values (1, 'sb');
            """.trimIndent()
            )
        }
        x.createStatement().use {
            val r = it.executeQuery(
                """
                select * from demo;
            """.trimIndent()
            )
            while (r.next()) {
                val id = r.getInt("id")
                assertEquals(1, id)
                val name = r.getString("name")
                assertEquals("sb", name)
                println("${r.getInt("id")}  ${r.getString("name")}")
            }
            r.close()
        }
        x.close()
    }

    @Order(5)
    @Test
    fun testGetDB() {
        val sqliteToken = DBResourcesProvider.grantDatabase("sqlite_test", "null", "null")
        val x = DBResourcesProvider.getDatabase("sqlite_test", sqliteToken)
        for (row in x.from(Demo).select()) {
            assertEquals(1, row[Demo.id])
            assertEquals("sb", row[Demo.name])
            println(row[Demo.id])
            println(row[Demo.name])
        }

        val y = DBResourcesProvider.getPool("sqlite_test", sqliteToken)
        for (row in y.from(Demo).select()) {
            assertEquals(1, row[Demo.id])
            assertEquals("sb", row[Demo.name])
            println(row[Demo.id])
            println(row[Demo.name])
        }
    }

    @Order(6)
    @Test
    fun testPG() {
        val tmp = mutableMapOf<Int, String>()
        val pgToken = DBResourcesProvider.grantDatabase("pg_test", "ktmeta_test", "ktmeta")
        val x = DBResourcesProvider.getDatabase("pg_test", pgToken)
        x.insertAndGenerateKey(PG) {
            PG.name to "them"
        }
        x.insertAndGenerateKey(PG) {
            PG.name to "him"
        }
        for (row in x.from(PG).select()) {
            tmp[row[PG.id] as Int] = row[PG.name] as String
        }
        assert(1 in tmp.keys)
        assert(2 in tmp.keys)
        assertEquals("them", tmp[1])
        assertEquals("him", tmp[2])
        DBResourcesProvider.getConnection("pg_test", pgToken).createStatement().use {
            it.executeUpdate(
                """
                DELETE FROM sb;
            """.trimIndent()
            )
        }
    }

    @Order(7)
    @Test
    fun testRemove() {
        println(DBResourcesProvider.checkCatalog())
        assertEquals(false, DBResourcesProvider.removeDatabase("nmsl", "fake"))
        assertEquals(
            true, DBResourcesProvider.removeDatabase(
                "pg_test",
                DBResourcesProvider.grantDatabase("pg_test", "ktmeta_test", "ktmeta")
            )
        )
        println(DBResourcesProvider.checkCatalog())
        assertEquals(
            true, DBResourcesProvider.removeDatabase(
                "sqlite_test",
                DBResourcesProvider.grantDatabase("sqlite_test", "null", "null")
            )
        )
        println(DBResourcesProvider.checkCatalog())
        println(DBRegCatalog.keys())
    }
}