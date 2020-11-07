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

package es

import io.github.hochikong.ktmeta.elasticsearch.ESMaintainer
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestESMaintainer {
    companion object {
        @JvmStatic
        @AfterAll
        fun afterAll() {
            ESMaintainer.dropTable()
        }

        @JvmStatic
        @BeforeAll
        fun beforAll() {
            if (ESMaintainer.hasTable()) {
                ESMaintainer.dropTable()
            }
        }
    }

    @Order(1)
    @Test
    fun testInit() {
        assertEquals(false, ESMaintainer.hasTable())
        ESMaintainer.createTable()
    }

    @Order(2)
    @Test
    fun testInsertRow() {
        assertEquals(true, ESMaintainer.insertRow("es index 1", "index 1", "http1"))
        println("\n Order 2: ${ESMaintainer.queryAllRows()}")
    }

    @Order(3)
    @Test
    fun testUpdate() {
        assertEquals(true, ESMaintainer.updateRowByID(1, "index_name", "'es index 1 fix'"))
        println("\n Order 3: ${ESMaintainer.queryAllRows()}")
    }

    @Order(4)
    @Test
    fun testDeleteRow() {
        println("\n Order 4 head: ${ESMaintainer.queryAllRows()}")
        assertEquals(true, ESMaintainer.deleteRowByID(1))
        println("\n Order 4 middle: ${ESMaintainer.queryAllRows()}")
        assertEquals(false, ESMaintainer.deleteRowByID(2))
        println("\n Order 4 tail: ${ESMaintainer.queryAllRows()}")
    }
}