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

/**
 * @author Hochikong
 * Use sqlite to save db registration info.
 * */
package io.github.hochikong.ktmeta.dbmgmt

import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object Maintainer {
    private val loggerM = LoggerFactory.getLogger("ktmeta->dbmgmt")

    private val columnNames = List(7) {
        SQLiteDBRegColumnConstrains[it].first
    }
    private const val driverStr = "org.sqlite.JDBC"
    private var dbURL = "jdbc:sqlite:dbreg.db"
    private lateinit var connection: Connection
    private var hasConnection: Boolean = false

    init {
        Class.forName(driverStr)
    }

    private fun checkConnection() {
        if (!hasConnection) {
            connection = DriverManager.getConnection(dbURL)
            hasConnection = true
        }
    }

    /**
     * Close connection, controlled by User.
     * */
    fun closeConnection() {
        if (hasConnection) {
            connection.close()
            hasConnection = false
        }
    }

    /**
     * Set [newUrl] as dbURL
     * */
    fun setDBUrl(newUrl: String): Boolean {
        if (!newUrl.contains("jdbc:sqlite")) return false
        if (!newUrl.contains("dbreg.db")) return false
        dbURL = newUrl
        return true
    }

    /**
     * Check dbreg.db contains registration table or not.
     * */
    fun hasTable(): Boolean {
        val sql = "SELECT id FROM registration;"
        checkConnection()
        connection.createStatement().use {
            return try {
                it.executeQuery(sql)
                true
            } catch (e: SQLException) {
                // loggerM.error("SQL: $sql, $e")
                false
            }
        }
    }

    /**
     * Create empty table called registration.
     *
     * DDL explanation:
     * - id: Integer and primary key, auto increment.
     * - db: Text, store supported dbs' identity.
     * - user: Text or null, store database's username. If you use sqlite it should be null.
     * - password: Text or null, store database's password after encryption. If you use sqlite it should be null.
     * - name: Text, not null and unique, store the name of this database.
     * - description: Text, not null, store the description of this database.
     * - url: Text, not null and unique, store the jdbc url of this database.
     * - protected: Integer, not full, store true as 1 and false as 0. If you use sqlite it should be 0.
     *
     * Execute DDL below:
     * ```SQL
     * CREATE TABLE registration(
     *  id INTEGER PRIMARY KEY AUTOINCREMENT ,
     *  db TEXT NOT NULL ,
     *  user TEXT,
     *  password TEXT,
     *  name TEXT NOT NULL UNIQUE,
     *  description TEXT NOT NULL ,
     *  url TEXT NOT NULL UNIQUE ,
     *  protected INTEGER NOT NULL ,
     *  CONSTRAINT db_type_check CHECK ( db in ('Sqlite', 'Postgresql') ),
     *  CONSTRAINT is_protected CHECK ( protected in (0, 1) )
     * );
     * ```
     *
     * */
    fun createTable(): Boolean {
        checkConnection()
        if (this.hasTable()) {
            return false
        }
        val sql = """
            CREATE TABLE registration(
                id INTEGER PRIMARY KEY AUTOINCREMENT ,
                db TEXT NOT NULL ,
                user TEXT,
                password TEXT,
                name TEXT NOT NULL UNIQUE,
                description TEXT NOT NULL ,
                url TEXT NOT NULL UNIQUE ,
                protected INTEGER NOT NULL ,
                CONSTRAINT db_type_check CHECK ( db in ('Sqlite', 'Postgresql') ),
                CONSTRAINT is_protected CHECK ( protected in (0, 1) )
            );
        """.trimIndent()
        connection.createStatement().use {
            return try {
                it.execute(sql)
                true
            } catch (e: SQLException) {
                loggerM.error("SQL: $sql, $e")
                false
            }
        }
    }

    /**
     * Insert a row to table.
     * @param data List, contains values of 'db', 'user', 'password', 'name', 'description', 'url', 'protected'.
     *
     * Check createTable()'s document for more information about these columns.
     *
     * If you want to insert a string, you must use "'CONTENT'" to cover it,
     * but if you insert null or integer, just simply use "CONTENT".
     *
     * */
    fun insertRow(data: List<Any>): Boolean {
        checkConnection()
        val sql = """
                INSERT INTO registration(db, user, password, name, description, url, protected)
                VALUES (${data[0]}, ${data[1]}, ${data[2]}, ${data[3]}, ${data[4]}, ${data[5]}, ${data[6]});
                """.trimIndent()
        return connection.createStatement().use {
            try {
                it.executeUpdate(sql)
                true
            } catch (e: SQLException) {
                loggerM.error("SQL: $sql, $e")
                false
            }
        }
    }

    /**
     * Return a list which contains all rows of registration table.
     * */
    fun queryAllRows(): List<List<Any>>? {
        checkConnection()
        val result = mutableListOf<List<Any>>()
        val sql = "SELECT * FROM registration;"
        connection.createStatement().use { it ->
            try {
                val queryResult = it.executeQuery(sql)
                while (queryResult.next()) {
                    val tmp = mutableListOf<Any>()
                    for (p in SQLiteDBRegColumnConstrains) {
                        if (p.second == 0) {
                            tmp.add(queryResult.getInt(p.first))
                        }
                        if (p.second == 1) {
                            tmp.add(queryResult.getString(p.first) ?: "null")
                        }
                    }
                    result.add(tmp.toList())
                }
                queryResult.close()
                return result.toList()
            } catch (e: SQLException) {
                loggerM.error("SQL: $sql, $e")
                return null
            }
        }
    }

    /**
     * Update row(s) in registration table by condition [where].
     * */
    fun updateRow(column: String, newValue: String, where: String): Boolean {
        checkConnection()
        require(column in columnNames) { "Column $column not exists." }
        val sql = """
                    UPDATE registration SET $column=$newValue WHERE $where;
                """.trimIndent()
        connection.createStatement().use {
            return try {
                it.executeUpdate(sql)
                true
            } catch (e: SQLException) {
                loggerM.error("SQL: $sql, $e")
                false
            }
        }
    }

    /**
     * Delete row(s) from registration table by condition(s) [where].
     * */
    fun deleteRow(where: String): Boolean {
        checkConnection()
        val sql = """
                    DELETE FROM registration WHERE $where ;
                  """.trimIndent()
        connection.createStatement().use {
            return try {
                it.executeUpdate(sql)
                true
            } catch (e: SQLException) {
                loggerM.error("SQL: $sql, $e")
                false
            }
        }
    }

    /**
     * Drop table
     * */
    fun dropTable(): Boolean {
        checkConnection()
        val sql = """
            DROP TABLE registration;
        """.trimIndent()
        connection.createStatement().use {
            return try {
                it.execute(sql)
                true
            } catch (e: SQLException) {
                loggerM.error("SQL: $sql, $e")
                false
            }
        }
    }
}