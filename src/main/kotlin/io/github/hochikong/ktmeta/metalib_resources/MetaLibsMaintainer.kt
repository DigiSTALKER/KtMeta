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
import me.liuwj.ktorm.support.sqlite.SQLiteDialect
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/**
 * Metadata libraries registration manager
 * */
object MetaLibsMaintainer {
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
     * CREATE TABLE IF NOT EXISTS metalibs
     * (
     * id   INTEGER PRIMARY KEY AUTOINCREMENT,
     * name TEXT NOT NULL UNIQUE,
     * desc TEXT NOT NULL,
     * assign_db REFERENCES dbs (name) UNIQUE,
     * assign_index REFERENCES indices (name) UNIQUE
     * );
     * */
    fun createTables(): Boolean {
        checkConnection()
        val sqlMeta = """
            CREATE TABLE IF NOT EXISTS metalibs
            (
                id   INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE ,
                desc TEXT NOT NULL ,
                assign_plugin REFERENCES plugins (name) ,
                assign_db REFERENCES dbs_registration (database) UNIQUE ,
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
//
//    fun insertRow():Boolean{}
//    fun queryAllRows(){}
//    fun updateRow():Boolean{}
//    fun deleteRow():Boolean{}
//    fun dropTable():Boolean{}

}

