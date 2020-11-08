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

package io.github.hochikong.ktmeta.metaplugin

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
 * Metadata management plugins registration manager
 * */
object MetaPluginMaintainer {
    private val loggerM = LoggerFactory.getLogger("ktmeta->mpmgmt")
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
            SELECT 1 FROM meta_plugins_registration;
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
     * DDL of meta_plugins_registration:
     * CREATE TABLE IF NOT EXISTS meta_plugins_registration(
     * id INTEGER PRIMARY KEY AUTOINCREMENT ,
     * plugin_name TEXT NOT NULL UNIQUE ,
     * plugin_version TEXT NOT NULL UNIQUE ,
     * plugin_class_name TEXT NOT NULL ,
     * plugin_desc TEXT NOT NULL ,
     * plugin_helper TEXT NOT NULL
     * );
     *
     * Explanation:
     * id: unique id which represent this plugin
     * plugin_name: Unique name for this plugin, relate to 'PluginRegistName' in MANIFEST.MF
     * plugin_version: Unique version for this plugin, relate to 'PluginVersion' in MANIFEST.MF
     * plugin_class_name: Class name for ktmeta to load this plugin, relate to 'PluginClassName' in MANIFEST.MF
     * plugin_desc: Description of this plugin
     * plugin_helper: Helper text of this plugin
     * */
    fun createTable(): Boolean {
        val sql = """
            CREATE TABLE IF NOT EXISTS meta_plugins_registration(
                id INTEGER PRIMARY KEY AUTOINCREMENT ,
                plugin_name TEXT NOT NULL UNIQUE ,
                plugin_version TEXT NOT NULL UNIQUE ,
                plugin_class_name TEXT NOT NULL ,
                plugin_desc TEXT NOT NULL ,
                plugin_helper TEXT NOT NULL
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

    object meta_plugins_registration : Table<Nothing>("meta_plugins_registration") {
        val id = int("id").primaryKey()
        val p_name = varchar("plugin_name")
        val p_version = varchar("plugin_version")
        val p_cname = varchar("plugin_class_name")
        val p_desc = text("plugin_desc")
        val p_helper = text("plugin_helper")
    }

    fun insertRow(
        p_name: String,
        p_version: String,
        p_cname: String,
        p_desc: String,
        p_helper: String
    ): Boolean {
        checkDatabase()
        return try {
            val ef = db.insert(meta_plugins_registration) {
                it.p_name to p_name
                it.p_version to p_version
                it.p_cname to p_cname
                it.p_desc to p_desc
                it.p_helper to p_helper
            }
            ef > 0
        } catch (e: SQLiteException) {
            loggerM.error("Ktorm: $e")
            false
        }
    }

    fun queryAllRows(): List<MetaPluginRegRow> {
        checkDatabase()
        val result = mutableListOf<MetaPluginRegRow>()
        try {
            for (row in db.from(meta_plugins_registration).select()) {
                result.add(
                    MetaPluginRegRow(
                        row[meta_plugins_registration.id] ?: -1,
                        row[meta_plugins_registration.p_name] ?: "null",
                        row[meta_plugins_registration.p_version] ?: "null",
                        row[meta_plugins_registration.p_cname] ?: "null",
                        row[meta_plugins_registration.p_desc] ?: "null",
                        row[meta_plugins_registration.p_helper] ?: "null"
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
        require(column in MetaPluginRegRow.columnNames) { "Column $column not exists." }
        val sql = """
            UPDATE meta_plugins_registration SET $column = $newValue WHERE id = $id;
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
            val ef = db.delete(meta_plugins_registration) {
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
            DROP TABLE meta_plugins_registration;
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