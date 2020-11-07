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

package io.github.hochikong.ktmeta.meta

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/**
 * Metadata libraries manager
 * */
object MetaLibsMaintainer {
    private const val driverStr = "org.sqlite.JDBC"
    private var dbURL = "jdbc:sqlite:metalibs.db"
    private lateinit var connection: Connection
    private var hasConnection: Boolean = false

    private fun checkConnection() {
        if (!MetaLibsMaintainer.hasConnection) {
            MetaLibsMaintainer.connection = DriverManager.getConnection(MetaLibsMaintainer.dbURL)
            MetaLibsMaintainer.hasConnection = true
        }
    }

    private fun hasTables(): Boolean {
        val sql1 = "SELECT 1 FROM metalibs;"
        val sql2 = "SELECT 1 FROM dbs;"
        val sql3 = "SELECT 1 FROM indices;"
        checkConnection()
        connection.createStatement().use {
            return try {
                it.executeQuery(sql1)
                it.executeQuery(sql2)
                it.executeQuery(sql3)
                true
            } catch (e: SQLException) {
                // loggerM.error("SQL: $sql, $e")
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
     *
     *
     * DDL of 'dbs' table:
     * CREATE TABLE IF NOT EXISTS dbs
     * (
     * name TEXT NOT NULL UNIQUE
     * );
     *
     *
     * DDL of 'indices' table:
     * CREATE TABLE IF NOT EXISTS indices
     * (
     * name TEXT NOT NULL UNIQUE
     * );
     * */
    private fun createTables(): Boolean {
        checkConnection()
        val sqlDBS = """
            CREATE TABLE IF NOT EXISTS dbs
            (
                name TEXT NOT NULL UNIQUE
            );
        """.trimIndent()

        val sqlIND = """
            CREATE TABLE IF NOT EXISTS indices
            (
                name TEXT NOT NULL UNIQUE
            );
        """.trimIndent()

        val sqlMeta = """
            CREATE TABLE IF NOT EXISTS metalibs
            (
                id   INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE ,
                desc TEXT NOT NULL ,
                assign_db REFERENCES dbs (name) UNIQUE ,
                assign_index REFERENCES indices (name) UNIQUE
            );
        """.trimIndent()
        connection.createStatement().use {
            return try {
                it.execute(sqlDBS)
                it.execute(sqlIND)
                it.execute(sqlMeta)
                true
            } catch (e: SQLException) {
                false
            }
        }
    }
}

