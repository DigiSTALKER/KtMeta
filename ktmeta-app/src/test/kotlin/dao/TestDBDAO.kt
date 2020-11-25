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

import io.github.hochikong.ktmeta.dao.DBRecord
import io.github.hochikong.ktmeta.dao.impl.DBResourceDAO
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDBDAO {
    companion object {
        @JvmStatic
        @AfterAll
        fun doAfter() {
            DBResourceDAO.resetTable()
        }

        @JvmStatic
        @BeforeAll
        fun doBefore() {
            DBResourceDAO.resetTable()
        }
    }

    private val dbData = DBRecord(
        dbms = "Sqlite",
        db_name = "db1",
        desc = "desc1",
        url = "url1",
        user = "null",
        password = "null",
        save_passwd = 0
    )


    @Order(1)
    @Test
    fun testInsert() {
        println("\n\n")
        assertEquals(true, DBResourceDAO.insertRecord(dbData))
        println("Order 1: ${DBResourceDAO.getAllRecords()}")
    }

    @Order(2)
    @Test
    fun testUpdate() {
        assertEquals(true, DBResourceDAO.updateRecord(1, dbData.copy(db_name = "new db1")))
        println("Order 2: ${DBResourceDAO.getAllRecords()}")
        assertEquals(false, DBResourceDAO.updateRecord(2, dbData))
        val x = DBResourceDAO.getRecordByName("new db1")
        println("Order 2: $x")
        assert(x is DBRecord)
        assert(DBResourceDAO.getRecordByName("SB") == null)
    }

    @Order(3)
    @Test
    fun testDelete() {
        assertEquals(false, DBResourceDAO.deleteRecord(2))
        assertEquals(true, DBResourceDAO.deleteRecord(1))
        println("Order 3: ${DBResourceDAO.getAllRecords()}")
    }
}
