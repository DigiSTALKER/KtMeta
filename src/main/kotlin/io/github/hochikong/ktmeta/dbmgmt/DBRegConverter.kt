/**
 * @author Hochikong
 * Convert input output for Maintainer and DBMgmt.
 * */
package io.github.hochikong.ktmeta.dbmgmt

import io.github.hochikong.ktmeta.predefined.ConvertError
import io.github.hochikong.ktmeta.predefined.SupportedDBs

/**
 * Columns' type constrain for Maintainer use SQLite .
 *
 * When Pair.second is 0, it means column type is Int, 1 means String.
 *
 * SQL DDL:
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
 * */
val SQLiteDBRegColumnConstrains = listOf(
    Pair("id", 0),
    Pair("db", 1),
    Pair("user", 1),
    Pair("password", 1),
    Pair("name", 1),
    Pair("description", 1),
    Pair("url", 1),
    Pair("protected", 0)
)


/**
 * Use this to store single row of registration table and convert to list for Maintainer.
 * */
data class RegRow(
    val id: Int = -1,
    var db: SupportedDBs = SupportedDBs.NotSupported,
    var user: String = "",
    var password: String = "",
    var name: String = "",
    var description: String = "",
    var url: String = "",
    var protected: Boolean = false
) {
    /**
     * Convert data class to list for Maintainer.insertRow().
     * */
    fun regIn(): List<Any> {
        if (db != SupportedDBs.NotSupported) {
            val result = mutableListOf<Any>()
            result.add("'${db.identity}'")
            result.add(if (user == "null") "null" else "'$user'")
            result.add(if (password == "null") "null" else "'$password'")
            result.add("'$name'")
            result.add("'$description'")
            result.add("'$url'")
            result.add(if (protected) 1 else 0)
            return result.toList()
        } else {
            throw ConvertError("RegRow -> func_regIn said: Convert failed.")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RegRow

        if (id != other.id) return false
        if (db != other.db) return false
        if (user != other.user) return false
        if (password != other.password) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (url != other.url) return false
        if (protected != other.protected) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + db.hashCode()
        result = 31 * result + user.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + protected.hashCode()
        return result
    }

    override fun toString(): String {
        return "RegRow(id=$id, db=$db, user='$user', password='$password', " +
                "name='$name', description='$description', url='$url', protected=$protected)"
    }
}

/**
 * Use this to parse single row of registration table.
 * @return Data class, RegRow
 * */
fun List<Any>.regOut(): RegRow {
    if (this.size != 8) throw ConvertError("List.regOut said: This list's size not equals to 8.")
    if (this[1] !in listOf(SupportedDBs.SQLite.identity, SupportedDBs.PostgreSQL.identity)) {
        throw ConvertError("List.regOut said: Database not supported.")
    }
    if (this[7] !in listOf(0, 1)) throw ConvertError("List.regOut said: RegRow_attr_protected set failed.")

    return RegRow(
        id = this[0] as Int,
        db = when (this[1] as String) {
            "Sqlite" -> SupportedDBs.SQLite
            "Postgresql" -> SupportedDBs.PostgreSQL
            else -> throw ConvertError("List.regOut attr_db said: Database not supported.")
        },
        user = this[2] as String,
        password = this[3] as String,
        name = this[4] as String,
        description = this[5] as String,
        url = this[6] as String,
        protected = this[7] as Int != 0
    )
}

