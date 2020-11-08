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
import io.github.hochikong.ktmeta.db_resources.regOut
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDBMaintainer {
    /**
     * id INTEGER PRIMARY KEY AUTOINCREMENT ,
     * dbms TEXT NOT NULL ,
     * alias TEXT NOT NULL,
     * user TEXT,
     * password TEXT,
     * database TEXT NOT NULL UNIQUE,
     * description TEXT NOT NULL ,
     * url TEXT NOT NULL ,
     * protected INTEGER NOT NULL ,
     * */
    private val legalData = listOf(
        listOf("'Sqlite'", "'Localhost-Sqlite'", "null", "null", "'test name1'", "'desc sqlite'", "'url 1'", '0'),
        listOf(
            "'Postgresql'",
            "'Network-PG'",
            "'postgres'",
            "'postgres'",
            "'test name2'",
            "'desc postgresql'",
            "'url 2'",
            '1'
        ),
        listOf(
            "'Sqlite'",
            "'Test-Sqlite'",
            "null",
            "null",
            "'test name 2'",
            "'desc sqlite 2'",
            "'url2'",
            "0"
        )
    )

    private val illegalData =
        listOf("'Mysql'", "'Network-mysql'", "'mysql'", "'mysql'", "'test name error'", "'desc mysql'", "'url 3'", '1')

    companion object {
        @JvmStatic
        @AfterAll
        fun afterAll() {
            DBMaintainer.dropTable()
        }

        @JvmStatic
        @BeforeAll
        fun beforAll() {
            if (DBMaintainer.hasTable()) {
                DBMaintainer.dropTable()
            }
        }
    }

    @Order(1)
    @Test
    fun testMaintainer() {
        println("tm")
        assertEquals(false, DBMaintainer.hasTable())
        DBMaintainer.createTable()
    }

    @Order(2)
    @Test
    fun testInsertTable() {
        assertEquals(true, DBMaintainer.createTable())
        assertEquals(true, DBMaintainer.insertRow(legalData[0]))
        assertEquals(true, DBMaintainer.insertRow(legalData[1]))
    }

    @Order(3)
    @Test
    fun testQueryTable() {
        DBMaintainer.createTable()
        assertEquals(false, DBMaintainer.insertRow(legalData[0]))
        val result = DBMaintainer.queryAllRows()
        println(result)
        assertEquals(
            listOf(1, "Sqlite", "Localhost-Sqlite", "null", "null", "test name1", "desc sqlite", "url 1", 0),
            result?.get(0)
        )
    }

    @Order(4)
    @Test
    fun testIllegal() {
        assertEquals(false, DBMaintainer.insertRow(illegalData))
    }

    @Order(5)
    @Test
    fun testUpdateAndDelete() {
        assertThrows<IllegalArgumentException> {
            DBMaintainer.updateRow(
                "sb",
                "222",
                "protected == 1"
            )
        }
        DBMaintainer.updateRow(
            "description",
            "'new desc sqlite'",
            "dbms == 'Sqlite' AND protected == 0"
        )
        assertEquals(
            listOf(
                1,
                "Sqlite",
                "Localhost-Sqlite",
                "null",
                "null",
                "test name1",
                "new desc sqlite",
                "url 1",
                0
            ).toString(),
            DBMaintainer.queryAllRows()?.get(0).toString()
        )
        DBMaintainer.deleteRow("dbms == 'Sqlite'")
        assertEquals(
            listOf(
                2,
                "Postgresql",
                "Network-PG",
                "postgres",
                "postgres",
                "test name2",
                "desc postgresql",
                "url 2",
                '1'
            ).toString(),
            DBMaintainer.queryAllRows()?.get(0).toString()
        )
        println("currnet all rows ${DBMaintainer.queryAllRows()}")
    }

    @Order(5)
    @Test
    fun testUpdateAndDeleteByUpdate() {
        assertEquals(true, DBMaintainer.insertRow(legalData[2]))
        val rr = DBMaintainer.queryAllRows()!![1].regOut()
        println("\n Order 5 is $rr")

        DBMaintainer.updateRowByID(3, "alias", "'test-sqlite fix'")
        assertEquals(
            listOf(
                3,
                "Sqlite",
                "test-sqlite fix",
                "null",
                "null",
                "test name 2",
                "desc sqlite 2",
                "url2",
                "0"
            ).toString(), DBMaintainer.queryAllRows()?.get(1).toString()
        )

        assertEquals(true, DBMaintainer.deleteRowByID(2))

        println("\n Last result ${DBMaintainer.queryAllRows()}")
    }
}