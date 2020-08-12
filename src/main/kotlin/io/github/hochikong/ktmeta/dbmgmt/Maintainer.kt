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
    private const val dbURL = "jdbc:sqlite:dbreg.db"
    private var connection: Connection

    init {
        Class.forName(driverStr)
        connection = DriverManager.getConnection(dbURL)
    }

    /**
     * Check dbreg.db contains registration table or not.
     * */
    fun hasTable(): Boolean {
        val sql = "SELECT id FROM registration;"
        connection.createStatement().use {
            return try {
                val result = it.executeQuery(sql)
                result.close()
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
     * Execute DDL below:
     * ```SQL
     * CREATE TABLE registration(
     *  id INTEGER PRIMARY KEY AUTOINCREMENT ,
     *  db TEXT NOT NULL ,
     *  user TEXT,
     *  password TEXT,
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
        if (this.hasTable()) {
            return false
        }
        val sql = """
            CREATE TABLE registration(
                id INTEGER PRIMARY KEY AUTOINCREMENT ,
                db TEXT NOT NULL ,
                user TEXT,
                password TEXT,
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
     * @param data List, contains values of 'db', 'user', 'password', 'description', 'url', 'protected'.
     *
     * Check createTable()'s document for more information about these columns.
     *
     * If you want to insert a string, you must use "'CONTENT'" to cover it,
     * but if you insert null or integer, just simply use "CONTENT".
     *
     * */
    fun insertRow(data: List<Any>): Boolean {
        val sql = """
                INSERT INTO registration(db, user, password, description, url, protected)
                VALUES (${data[0]}, ${data[1]}, ${data[2]}, ${data[3]}, ${data[4]}, ${data[5]});
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
    fun queryAllRow(): List<List<Any>>? {
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
        val sql = """
                    DELETE FROM registration WHERE $where ;
                  """.trimIndent()
        connection.createStatement().use {
            return try {
                println(sql)
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