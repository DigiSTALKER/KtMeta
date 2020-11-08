package db/*
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

import io.github.hochikong.ktmeta.db_resources.DBMaintainer
import io.github.hochikong.ktmeta.db_resources.DBRegCatalog
import io.github.hochikong.ktmeta.db_resources.RegRow
import io.github.hochikong.ktmeta.predefined.SupportedDBs
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class TestContainers {
    private val data = RegRow(
        -1,
        SupportedDBs.PostgreSQL,
        "My postgres",
        "postgres",
        "postgres",
        "testdb",
        "this is a test db",
        "jdbc:postgresql",
        true
    )
    private val data1 = RegRow(
        -1,
        SupportedDBs.SQLite,
        "My sqlite",
        "null",
        "null",
        "sqlite",
        "this is a test db for sqlite",
        "jdbc:sqlite",
        false
    )

    companion object {
        @JvmStatic
        @BeforeAll
        fun bA() {
            if (!DBMaintainer.hasTable()) {
                DBMaintainer.createTable()
            } else {
                DBMaintainer.dropTable()
                DBMaintainer.createTable()
            }
        }

        @JvmStatic
        @AfterAll
        fun aA() {
            DBMaintainer.dropTable()
        }
    }

    @Test
    fun testDBRegCatalog() {
        DBMaintainer.insertRow(data.regIn())
        DBMaintainer.insertRow(data1.regIn())
        DBRegCatalog.updateCatalog(DBMaintainer.queryAllRows())
        assertEquals(2, DBRegCatalog.keys().size)

        val result = DBRegCatalog["testdb"]
        if (result != null) {
            assertEquals(1, result.id)
            assertEquals("My postgres", result.alias)
            assertEquals("postgres", result.user)
            assertEquals("postgres", result.password)
            assertEquals(SupportedDBs.PostgreSQL, result.dbms)
            assertEquals("this is a test db", result.description)
            assertEquals("jdbc:postgresql", result.url)
            assertEquals(true, result.protected)
        }

        val result1 = DBRegCatalog["sqlite"]
        if (result1 != null) {
            assertEquals(2, result1.id)
            assertEquals("My sqlite", result1.alias)
            assertEquals("null", result1.user)
            assertEquals("null", result1.password)
            assertEquals(SupportedDBs.SQLite, result1.dbms)
            assertEquals("this is a test db for sqlite", result1.description)
            assertEquals("jdbc:sqlite", result1.url)
            assertEquals(false, result1.protected)

            assertEquals(false, DBRegCatalog.updateCatalog(null))
            assertEquals(null, DBRegCatalog["nmsl"])
        }
    }
}