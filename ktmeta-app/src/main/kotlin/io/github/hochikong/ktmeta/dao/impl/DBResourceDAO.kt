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

package io.github.hochikong.ktmeta.dao.impl

import com.zaxxer.hikari.HikariDataSource
import io.github.hochikong.ktmeta.dao.*
import me.liuwj.ktorm.database.Database
import me.liuwj.ktorm.dsl.*
import org.slf4j.LoggerFactory
import org.sqlite.SQLiteException

object DBResourceDAO : ResourcesDAOAPI {
    private const val tableName = DBRecord.tableName

    private val checkTableSQL = """
        SELECT 1 FROM $tableName;
    """.trimIndent()
    private val resetSQL = """
        DROP TABLE $tableName;
    """.trimIndent()
    private val DDL = DBRecord.ddl

    private val db: Database
    private val dataSource = HikariDataSource(DAOConfig.buildCPConfig(tableName))
    private val logger = LoggerFactory.getLogger(DAOConfig.logKey[tableName])

    private fun checkTable() {
        db.useConnection { connection ->
            connection.createStatement().use {
                try {
                    it.execute(checkTableSQL)
                } catch (e: SQLiteException) {
                    logger.info("Solved: $e")
                    // when no such table
                    it.execute(DDL)
                }
            }
        }
    }

    init {
        db = Database.connect(dataSource)
        checkTable()
    }

    override fun insertRecord(record: ResourcesRecord): Boolean {
        val r = record as DBRecord
        val ef = db.insert(DBRegTable) {
            it.dbms to r.dbms
            it.db_name to r.db_name
            it.desc to r.desc
            it.url to r.url
            it.user to r.user
            it.password to r.password
            it.save_passwd to r.save_passwd
        }
        logger.info("Insert: $r")
        return ef > 0
    }

    override fun updateRecord(id: Int, newRecord: ResourcesRecord): Boolean {
        val r = newRecord as DBRecord
        val ef = db.update(DBRegTable) {
            it.dbms to r.dbms
            it.db_name to r.db_name
            it.desc to r.desc
            it.url to r.url
            it.user to r.user
            it.password to r.password
            it.save_passwd to r.save_passwd
            where {
                it.id eq id
            }
        }
        logger.info("Update: $r")
        return ef > 0
    }

    fun getRecordByName(dbName: String): DBRecord? {
        // DBRegTable.name is unique
        var r: DBRecord? = null
        for (row in db.from(DBRegTable).select().where { DBRegTable.db_name eq dbName }) {
            if (row[DBRegTable.db_name] != null && row[DBRegTable.db_name] == dbName) {
                r = DBRecord(
                    row[DBRegTable.id]!!,
                    row[DBRegTable.dbms]!!,
                    row[DBRegTable.db_name]!!,
                    row[DBRegTable.desc]!!,
                    row[DBRegTable.url]!!,
                    row[DBRegTable.user]!!,
                    row[DBRegTable.password]!!,
                    row[DBRegTable.save_passwd]!!
                )
            }
        }
        return r
    }

    override fun getAllRecords(): List<ResourcesRecord> {
        val result = mutableListOf<DBRecord>()
        for (row in db.from(DBRegTable).select()) {
            result.add(
                DBRecord(
                    row[DBRegTable.id] ?: -1,
                    row[DBRegTable.dbms] ?: "null",
                    row[DBRegTable.db_name] ?: "null",
                    row[DBRegTable.desc] ?: "null",
                    row[DBRegTable.url] ?: "null",
                    row[DBRegTable.user] ?: "null",
                    row[DBRegTable.password] ?: "null",
                    row[DBRegTable.save_passwd] ?: -1
                )
            )
        }
        return result.toList()
    }

    override fun deleteRecord(id: Int): Boolean {
        val ef = db.delete(DBRegTable) {
            it.id eq id
        }
        logger.info("Delete: id=$id")
        return ef > 0
    }

    override fun resetTable(): Boolean {
        db.useConnection { connection ->
            connection.createStatement().use {
                it.execute(resetSQL)
                it.execute(DDL)
            }
        }
        logger.info("Drop Table")
        return true
    }
}