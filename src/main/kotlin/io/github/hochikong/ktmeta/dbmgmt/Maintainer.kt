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
    /* When 0, it means Int, 1 means String */
    private val columnConstrains = listOf(
        Pair("id", 0),
        Pair("db", 1),
        Pair("user", 1),
        Pair("password", 1),
        Pair("description", 1),
        Pair("url", 1),
        Pair("protected", 0)
    )
    private val logger = LoggerFactory.getLogger("Logger from Maintainer.kt")
    private val columnNames = List(7) {
        columnConstrains[it].first
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
        val query = "SELECT id FROM registration;"
        connection.createStatement().use {
            return try {
                val result = it.executeQuery(query)
                result.close()
                true
            } catch (e: SQLException) {
                println(e.toString())
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
                println(e.toString())
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
    fun insertData(data: List<Any>): Boolean {
        val sql = """
                INSERT INTO registration(db, user, password, description, url, protected)
                VALUES (${data[0]}, ${data[1]}, ${data[2]}, ${data[3]}, ${data[4]}, ${data[5]});
                """.trimIndent()
        return connection.createStatement().use {
            try {
                it.executeUpdate(sql)
                true
            } catch (e: SQLException) {
                println(e.toString())
                false
            }
        }
    }

    /**
     * Return a list which contains all rows of registration table.
     * */
    fun queryAllData(): List<List<Any>>? {
        val result = mutableListOf<List<Any>>()
        val sql = "SELECT * FROM registration;"
        connection.createStatement().use { it ->
            try {
                val queryResult = it.executeQuery(sql)
                while (queryResult.next()) {
                    val tmp = mutableListOf<Any>()
                    for (p in columnConstrains) {
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
                println(e.toString())
                return null
            }
        }
    }

    /**
     * Update row(s) in registration table by condition [where].
     * */
    fun updateRow(column: String, newValue: String, where: String): Boolean {
        require(column in columnNames) { "Column $column not exists." }
        connection.createStatement().use {
            return try {
                val sql = """
                    UPDATE registration SET $column=$newValue WHERE $where;
                """.trimIndent()
                it.executeUpdate(sql)
                true
            } catch (e: SQLException) {
                false
            }
        }
    }

    /**
     * Delete row(s) from registration table by condition(s) [where].
     * */
    fun deleteRow(where: String): Boolean {
        connection.createStatement().use {
            return try {
                val sql = """
                    DELETE FROM registration WHERE $where ;
                """.trimIndent()
                println(sql)
                it.executeUpdate(sql)
                true
            } catch (e: SQLException) {
                false
            }
        }
    }

    /**
     * Drop table
     * */
    fun dropTable(): Boolean {
        connection.createStatement().use {
            return try {
                it.execute("DROP TABLE registration;")
                true
            } catch (e: SQLException) {
                false
            }
        }
    }
}