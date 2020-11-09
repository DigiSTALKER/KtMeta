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

package io.github.hochikong.ktmeta.metalib_resources

import me.liuwj.ktorm.database.Database
import me.liuwj.ktorm.dsl.*
import me.liuwj.ktorm.schema.Table
import me.liuwj.ktorm.schema.int
import me.liuwj.ktorm.schema.varchar
import me.liuwj.ktorm.support.sqlite.SQLiteDialect
import org.slf4j.LoggerFactory
import org.sqlite.SQLiteException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/**
 * Metadata libraries registration manager
 * */
object MetaLibsMaintainer {
    private val loggerM = LoggerFactory.getLogger("ktmeta->mlmgmt")
    private const val driverStr = "org.sqlite.JDBC"
    private var dbURL = "jdbc:sqlite:resources.db"
    private lateinit var connection: Connection
    private var hasConnection: Boolean = false

    private lateinit var db: Database


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
        db = Database.connect(
            url = dbURL,
            driver = driverStr,
            dialect = SQLiteDialect()
        )
    }

    fun hasTables(): Boolean {
        val sql1 = """
            SELECT 1 FROM metalibs;
        """.trimIndent()
        checkConnection()
        connection.createStatement().use {
            return try {
                it.executeQuery(sql1)
                true
            } catch (e: SQLException) {
                false
            }
        }
    }

    // When hasTables return false execute this method
    /**
     * DDL of 'metalibs' table:
     * CREATE TABLE IF NOT EXISTS metalibs_registration
     * (
     * id       INTEGER PRIMARY KEY AUTOINCREMENT,
     * lib_name TEXT                                                    NOT NULL UNIQUE,
     * lib_desc TEXT                                                    NOT NULL,
     * assign_plugin REFERENCES metaplugins_registration (plugin_name) NOT NULL,
     * assign_db REFERENCES dbs_registration (database) UNIQUE,
     * assign_index REFERENCES indices_registration (index_name) UNIQUE
     * );
     * */
    fun createTables(): Boolean {
        checkConnection()
        val sqlMeta = """
            CREATE TABLE IF NOT EXISTS metalibs_registration
            (
                id       INTEGER PRIMARY KEY AUTOINCREMENT,
                lib_name TEXT                                                    NOT NULL UNIQUE,
                lib_desc TEXT                                                    NOT NULL,
                assign_plugin REFERENCES metaplugins_registration (plugin_name) NOT NULL,
                assign_db REFERENCES dbs_registration (database) UNIQUE,
                assign_index REFERENCES indices_registration (index_name) UNIQUE
            );
        """.trimIndent()
        connection.autoCommit = false
        connection.createStatement().use {
            return try {
                it.execute(sqlMeta)
                connection.commit()
                closeConnection()
                true
            } catch (e: SQLException) {
                connection.rollback()
                false
            }
        }
    }

    object metalibs_registration : Table<Nothing>("metalibs_registration") {
        val id = int("id").primaryKey()
        val name = varchar("lib_name")
        val desc = varchar("lib_desc")
        val plugin = varchar("assign_plugin")
        val db = varchar("assign_db")
        val index = varchar("assign_index")
    }

    fun insertRow(name: String, desc: String, plugin: String, db: String, index: String): Boolean {
        checkDatabase()
        return try {
            val ef = this.db.insert(metalibs_registration) {
                it.name to name
                it.desc to desc
                it.plugin to plugin
                it.db to db
                it.index to index
            }
            ef > 0
        } catch (e: SQLiteException) {
            loggerM.error("Ktorm: $e")
            false
        }
    }

    fun queryAllRows(): List<MLRegRow> {
        checkDatabase()
        val result = mutableListOf<MLRegRow>()
        try {
            for (row in db.from(metalibs_registration).select()) {
                result.add(
                    MLRegRow(
                        row[metalibs_registration.id] ?: -1,
                        row[metalibs_registration.name] ?: "null",
                        row[metalibs_registration.desc] ?: "null",
                        row[metalibs_registration.plugin] ?: "null",
                        row[metalibs_registration.db] ?: "null",
                        row[metalibs_registration.index] ?: "null"
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
        require(column in MLRegRow.columnNames) { "Column $column not exists." }
        val sql = """
            UPDATE metalibs_registration SET $column = $newValue WHERE id = $id;
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
            val ef = db.delete(metalibs_registration) {
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
            DROP TABLE metalibs_registration;
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

