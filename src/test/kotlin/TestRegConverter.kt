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

import io.github.hochikong.ktmeta.dbmgmt.Maintainer
import io.github.hochikong.ktmeta.dbmgmt.RegRow
import io.github.hochikong.ktmeta.dbmgmt.regOut
import io.github.hochikong.ktmeta.predefined.SupportedDBs
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class TestRegConverter {
    private val rawInput = RegRow(
        -1, SupportedDBs.SQLite, "null", "null",
        "test db", "desc sqlite", "url 1", false
    )
    private val input = listOf("'Sqlite'", "null", "null", "'test db'", "'desc sqlite'", "'url 1'", "0")
    private val rawOutput = listOf(1, "Sqlite", "null", "null", "test db", "desc sqlite", "url 1", 0)
    private val output = RegRow(1, SupportedDBs.SQLite, "null", "null", "test db", "desc sqlite", "url 1", false)

    companion object {
        @AfterAll
        @JvmStatic
        fun afterAll() {
            Maintainer.dropTable()
        }

        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            if (!Maintainer.hasTable()) {
                Maintainer.createTable()
            } else {
                Maintainer.dropTable()
                Maintainer.createTable()
            }
        }
    }

    @Test
    fun testRegIn() {
        assertEquals(input.toString(), rawInput.regIn().toString())
        assertEquals(output.toString(), rawOutput.regOut().toString())
    }

    @Test
    fun testRegInOutWithDB() {
        Maintainer.insertRow(rawInput.regIn())
        val tmp = Maintainer.queryAllRows()?.get(0)
        println(tmp)
        println(tmp?.regOut())
        assertEquals(output, tmp?.regOut())
    }
}