package io.github.hochikong.ktmeta.dbmgmt


/**
 * Supported DBs
 * */
enum class SupportedDBs(val identity: String) {
    SQLite("Sqlite"),
    PostgreSQL("Postgresql");

    override fun toString(): String {
        return this.identity
    }
}

/**
 * Columns' type constrain.
 *
 * SQL DDL:
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
 *  When Pair.second is 0, it means Int, 1 means String
 * */
val columnConstrains = listOf(
    Pair("id", 0),
    Pair("db", 1),
    Pair("user", 1),
    Pair("password", 1),
    Pair("description", 1),
    Pair("url", 1),
    Pair("protected", 0)
)

/**
 * Use regOut to convert result from Maintainer.
 *
 * SQL DDL:
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
 * */
fun List<Any>.regOut(): List<Any?>? {
    var result: List<Any?>? = null
    if ((this.isNotEmpty()) and (this.size == 7)) {
        result = when {
            (this[1] == SupportedDBs.PostgreSQL.identity) or (this[1] == SupportedDBs.SQLite.identity) -> {
                val tmp = mutableListOf<Any?>()
                for (index in (this.indices)) {
                    when {
                        this[index] == "null" -> tmp.add(null)
                        index == 6 -> when {
                            this[index] == 1 -> tmp.add(true)
                            else -> tmp.add(false)
                        }
                        (this[index] is Number) or (this[index] is String) -> tmp.add(this[index])
                    }
                }
                tmp
            }
            else -> null
        }
    }
    return result
}

/**
 * Use RegIn to convert input for Maintainer.
 *
 * SQL DDL:
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
 * SQL DML:
 * ```SQL
 * INSERT INTO registration(db, user, password, description, url, protected)
 * VALUES (xxx, etc.)
 * ```
 * */
fun List<Any?>.regIn(): List<Any>? {
    var result: List<Any>? = null
    if ((this.isNotEmpty()) and (this.size == 6)) {
        result = when {
            (this[0] == SupportedDBs.PostgreSQL.identity) or (this[0] == SupportedDBs.SQLite.identity) -> {
                val tmp = mutableListOf<Any>()
                for (ele in this) {
                    when (ele) {
                        null -> tmp.add("null")
                        is Number -> tmp.add("$ele")
                        is Char -> tmp.add("'$ele'")
                        is String -> tmp.add("'$ele'")
                        false -> tmp.add("0")
                        true -> tmp.add("1")
                        else -> print("do nothing")
                    }

                }
                tmp
            }
            else -> null
        }
    }
    return result
}