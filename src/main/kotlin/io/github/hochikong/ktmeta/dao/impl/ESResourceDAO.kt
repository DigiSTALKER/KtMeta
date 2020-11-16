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

object ESResourceDAO : ResourcesDAOAPI {
    private const val tableName = ESRecord.tableName

    private val checkTableSQL = """
        SELECT 1 FROM $tableName;
    """.trimIndent()
    private val resetSQL = """
        DROP TABLE $tableName;
    """.trimIndent()
    private val DDL = ESRecord.ddl

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
        val r = record as ESRecord
        val ef = db.insert(ESRegTable) {
            it.name to r.index_name
            it.desc to r.index_desc
            it.url to r.index_url
        }
        logger.info("Insert: $r")
        return ef > 0
    }

    override fun updateRecord(id: Int, newRecord: ResourcesRecord): Boolean {
        val r = newRecord as ESRecord
        val ef = db.update(ESRegTable) {
            it.name to r.index_name
            it.desc to r.index_desc
            it.url to r.index_url
            where {
                it.id eq id
            }
        }
        logger.info("Update: $r")
        return ef > 0
    }

    override fun getAllRecords(): List<ResourcesRecord> {
        val result = mutableListOf<ESRecord>()
        for (row in db.from(ESRegTable).select()) {
            result.add(
                ESRecord(
                    row[ESRegTable.id] ?: -1,
                    row[ESRegTable.name] ?: "null",
                    row[ESRegTable.desc] ?: "null",
                    row[ESRegTable.url] ?: "null"
                )
            )
        }
        return result.toList()
    }

    override fun deleteRecord(id: Int): Boolean {
        val ef = db.delete(ESRegTable) {
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