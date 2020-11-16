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

object MPResourceDAO : ResourcesDAOAPI {
    private const val tableName = MPRecord.tableName

    private val checkTableSQL = """
        SELECT 1 FROM $tableName;
    """.trimIndent()
    private val resetSQL = """
        DROP TABLE $tableName;
    """.trimIndent()
    private val DDL = MPRecord.ddl

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
        val r = record as MPRecord
        val ef = db.insert(MPRegTable) {
            it.name to r.plugin_name
            it.version to r.plugin_version
            it.cname to r.plugin_class_name
            it.desc to r.plugin_desc
            it.helper to r.plugin_helper
        }
        logger.info("Insert: $r")
        return ef > 0
    }

    override fun updateRecord(id: Int, newRecord: ResourcesRecord): Boolean {
        val r = newRecord as MPRecord
        val ef = db.update(MPRegTable) {
            it.name to r.plugin_name
            it.version to r.plugin_version
            it.cname to r.plugin_class_name
            it.desc to r.plugin_desc
            it.helper to r.plugin_helper
            where {
                it.id eq id
            }
        }
        logger.info("Update: $r")
        return ef > 0
    }

    override fun getAllRecords(): List<ResourcesRecord> {
        val result = mutableListOf<MPRecord>()
        for (row in db.from(MPRegTable).select()) {
            result.add(
                MPRecord(
                    row[MPRegTable.id] ?: -1,
                    row[MPRegTable.name] ?: "null",
                    row[MPRegTable.version] ?: "null",
                    row[MPRegTable.cname] ?: "null",
                    row[MPRegTable.desc] ?: "null",
                    row[MPRegTable.helper] ?: "null"
                )
            )
        }
        return result.toList()
    }

    override fun deleteRecord(id: Int): Boolean {
        val ef = db.delete(MPRegTable) {
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