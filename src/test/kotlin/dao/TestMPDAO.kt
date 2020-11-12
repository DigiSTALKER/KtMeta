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

package dao

import io.github.hochikong.ktmeta.dao.MPRecord
import io.github.hochikong.ktmeta.dao.ResourcesDAOAPI
import io.github.hochikong.ktmeta.dao.impl.MPResourceDAO
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestMPDAO {
    companion object {
        @JvmStatic
        @AfterAll
        @BeforeAll
        fun doAll() {
            MPResourceDAO.resetTable()
        }
    }

    private val mpData = MPRecord(
        plugin_name = "p name",
        plugin_version = "0.0.1",
        plugin_class_name = "xxx.pname",
        plugin_desc = "desc p name",
        plugin_helper = "p helper"
    )

    private val dao: ResourcesDAOAPI = MPResourceDAO

    @Order(1)
    @Test
    fun testInsert() {
        assertEquals(true, dao.insertRecord(mpData))
        println("Order 1: ${dao.getAllRecords()}")
    }

    @Order(2)
    @Test
    fun testUpdate() {
        assertEquals(true, dao.updateRecord(1, mpData.copy(plugin_class_name = "xxx.sb.pname")))
        println("Order 2: ${dao.getAllRecords()}")
        assertEquals(false, dao.updateRecord(2, mpData))
    }

    @Order(3)
    @Test
    fun testDelete() {
        assertEquals(false, dao.deleteRecord(2))
        assertEquals(true, dao.deleteRecord(1))
        println("Order 3: ${dao.getAllRecords()}")
    }
}