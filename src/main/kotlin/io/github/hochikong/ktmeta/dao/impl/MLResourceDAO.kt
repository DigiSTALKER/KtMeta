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
import me.liuwj.ktorm.support.sqlite.SQLiteDialect
import org.slf4j.LoggerFactory
import org.sqlite.SQLiteException

object MLResourceDAO : ResourcesDAOAPI {
    private const val tableName = MLRecord.tableName

    private val checkTableSQL = """
        SELECT 1 FROM $tableName;
    """.trimIndent()
    private val resetSQL = """
        DROP TABLE $tableName;
    """.trimIndent()
    private val DDL = MLRecord.ddl

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
        db = Database.connect(dataSource, SQLiteDialect())
        checkTable()
    }

    override fun insertRecord(record: ResourcesRecord): Boolean {
        val r = record as MLRecord
        val ef = db.insert(MLRegTable) {
            it.name to r.lib_name
            it.desc to r.lib_desc
            it.plugin to r.assign_plugin
            it.db to r.assign_db
            it.index to r.assign_index
        }
        logger.info("Insert: $r")
        return ef > 0
    }

    override fun updateRecord(id: Int, newRecord: ResourcesRecord): Boolean {
        val r = newRecord as MLRecord
        val ef = db.update(MLRegTable) {
            it.name to r.lib_name
            it.desc to r.lib_desc
            it.plugin to r.assign_plugin
            it.db to r.assign_db
            it.index to r.assign_index
            where {
                it.id eq id
            }
        }
        logger.info("Update: $r")
        return ef > 0
    }

    override fun getAllRecords(): List<ResourcesRecord> {
        val result = mutableListOf<MLRecord>()
        for (row in db.from(MLRegTable).select()) {
            result.add(
                MLRecord(
                    row[MLRegTable.id] ?: -1,
                    row[MLRegTable.name] ?: "null",
                    row[MLRegTable.desc] ?: "null",
                    row[MLRegTable.plugin] ?: "null",
                    row[MLRegTable.db] ?: "null",
                    row[MLRegTable.index] ?: "null"
                )
            )
        }
        return result.toList()
    }

    override fun deleteRecord(id: Int): Boolean {
        val ef = db.delete(MLRegTable) {
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