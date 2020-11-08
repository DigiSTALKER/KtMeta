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

package metaplugin

import io.github.hochikong.ktmeta.metaplugin.MetaPluginMaintainer
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestMetaPluginMaintainer {
    companion object {
        @JvmStatic
        @AfterAll
        fun afterAll() {
            MetaPluginMaintainer.dropTable()
        }

        @JvmStatic
        @BeforeAll
        fun beforAll() {
            if (MetaPluginMaintainer.hasTable()) {
                MetaPluginMaintainer.dropTable()
            }
        }
    }

    @Order(1)
    @Test
    fun testInit() {
        assertEquals(false, MetaPluginMaintainer.deleteRowByID(2))
        assertEquals(false, MetaPluginMaintainer.hasTable())
        assertEquals(true, MetaPluginMaintainer.createTable())
    }

    @Order(2)
    @Test
    fun testInsertQueryAndUpdate() {
        println("Order 2, Init res: ${MetaPluginMaintainer.queryAllRows()}")
        assertEquals(
            true, MetaPluginMaintainer.insertRow(
                "plugin 1",
                "0.1",
                "xxx.xx.plugin1",
                "desc 1",
                "helper 1"
            )
        )
        assertEquals(true, MetaPluginMaintainer.updateRowByID(
            1, "plugin_desc", "'desc 1 fix'"))
        println("Order 2, Updated res: ${MetaPluginMaintainer.queryAllRows()}")
    }

    @Order(3)
    @Test
    fun testDelete(){
        assertEquals(false, MetaPluginMaintainer.deleteRowByID(2))
        assertEquals(true, MetaPluginMaintainer.deleteRowByID(1))
        println("Order 3 res: ${MetaPluginMaintainer.queryAllRows()}")
    }
}