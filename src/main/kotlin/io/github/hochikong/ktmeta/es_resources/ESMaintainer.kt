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

package io.github.hochikong.ktmeta.es_resources

import me.liuwj.ktorm.database.Database
import me.liuwj.ktorm.dsl.*
import me.liuwj.ktorm.schema.Table
import me.liuwj.ktorm.schema.int
import me.liuwj.ktorm.schema.text
import me.liuwj.ktorm.schema.varchar
import me.liuwj.ktorm.support.sqlite.SQLiteDialect
import org.slf4j.LoggerFactory
import org.sqlite.SQLiteException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/**
 * ElasticSearch indices registration manager
 * */

object ESMaintainer {
    private val loggerM = LoggerFactory.getLogger("ktmeta->esmgmt")
    private const val driverStr = "org.sqlite.JDBC"
    private var dbURL = "jdbc:sqlite:resources.db"
    private lateinit var connection: Connection
    private var hasConnection: Boolean = false

    private lateinit var db: Database
    private var hasDB: Boolean = false

    init {
        Class.forName(driverStr)
    }

    private fun checkConnection() {
        if (!hasConnection) {
            connection = DriverManager.getConnection(dbURL)
            hasConnection = true
        }
    }

    private fun closeConnection() {
        if (hasConnection) {
            connection.close()
            hasConnection = false
        }
    }

    private fun checkDatabase() {
        if (!hasDB) {
            db = Database.connect(
                url = dbURL,
                driver = driverStr,
                dialect = SQLiteDialect()
            )
            hasDB = true
        }
    }

    fun hasTable(): Boolean {
        val sql = """
            SELECT 1 FROM indices_registration;
        """.trimIndent()
        checkConnection()
        connection.createStatement().use {
            return try {
                it.executeQuery(sql)
                true
            } catch (e: SQLException) {
                loggerM.error("SQL: $sql, $e")
                false
            }
        }
    }

    /**
     * DDL of 'indices_registration' table:
     * CREATE TABLE IF NOT EXISTS indices_registration(
     * id INTEGER PRIMARY KEY AUTOINCREMENT ,
     * index_name TEXT NOT NULL UNIQUE ,
     * index_desc TEXT NOT NULL ,
     * index_url TEXT NOT NULL UNIQUE
     * );
     *
     * */
    fun createTable(): Boolean {
        val sql = """
            CREATE TABLE IF NOT EXISTS indices_registration(
                id INTEGER PRIMARY KEY AUTOINCREMENT ,
                index_name TEXT NOT NULL UNIQUE ,
                index_desc TEXT NOT NULL ,
                index_url TEXT NOT NULL UNIQUE 
            );
        """.trimIndent()
        checkConnection()
        connection.createStatement().use {
            return try {
                it.execute(sql)
                closeConnection()
                true
            } catch (e: SQLException) {
                loggerM.error("SQL: $sql, $e")
                false
            }
        }
    }

    object indices_registration : Table<Nothing>("indices_registration") {
        val id = int("id").primaryKey()
        val ind_name = varchar("index_name")
        val ind_desc = text("index_desc")
        val ind_url = varchar("index_url")
    }

    fun insertRow(name: String, desc: String, url: String): Boolean {
        checkDatabase()
        return try {
            val ef = db.insert(indices_registration) {
                it.ind_name to name
                it.ind_desc to desc
                it.ind_url to url
            }
            ef > 0
        } catch (e: SQLiteException) {
            loggerM.error("Ktorm: $e")
            false
        }
    }

    fun queryAllRows(): List<ESRegRow> {
        checkDatabase()
        val result = mutableListOf<ESRegRow>()
        try {
            for (row in db.from(indices_registration).select()) {
                result.add(
                    ESRegRow(
                        row[indices_registration.id] ?: -1,
                        row[indices_registration.ind_name] ?: "null",
                        row[indices_registration.ind_desc] ?: "null",
                        row[indices_registration.ind_url] ?: "null"
                    )
                )
            }

        } catch (e: SQLiteException) {
            loggerM.error("Ktorm: $e")
        }
        return result
    }

    fun updateRowByID(id: Int, column: String, newValue: String): Boolean {
        checkConnection()
        require(column in ESRegRow.columnNames) { "Column $column not exists." }
        val sql = """
            UPDATE indices_registration SET $column = $newValue WHERE id = $id;
        """.trimIndent()
        connection.createStatement().use {
            return try {
                it.executeUpdate(sql)
                closeConnection()
                true
            } catch (e: SQLException) {
                loggerM.error("SQL: $sql, $e")
                closeConnection()
                false
            }
        }
    }

    fun deleteRowByID(id: Int): Boolean {
        checkDatabase()
        return try {
            val ef = db.delete(indices_registration) {
                it.id eq id
            }
            ef > 0
        } catch (e: SQLiteException) {
            loggerM.error("Ktorm: $e")
            false
        }
    }

    fun dropTable(): Boolean {
        checkConnection()
        val sql = """
            DROP TABLE indices_registration;
        """.trimIndent()
        connection.createStatement().use {
            return try {
                it.execute(sql)
                // automatically close connection
                closeConnection()
                true
            } catch (e: SQLException) {
                loggerM.error("SQL: $sql, $e")
                false
            }
        }
    }
}