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
 *  dbms TEXT NOT NULL ,
 *  alias TEXT NOT NULL,
 *  user TEXT,
 *  password TEXT,
 *  database TEXT NOT NULL UNIQUE,
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
    Pair("dbms", 1),
    Pair("alias", 1),
    Pair("user", 1),
    Pair("password", 1),
    Pair("database", 1),
    Pair("description", 1),
    Pair("url", 1),
    Pair("protected", 0)
)


/**
 * Use this to store single row of registration table and convert to list for Maintainer.
 * */
data class RegRow(
    val id: Int,
    var dbms: SupportedDBs,
    var alias: String,
    var user: String,
    var password: String,
    var database: String,
    var description: String,
    var url: String,
    var protected: Boolean
) {
    /**
     * Convert data class to list for Maintainer.insertRow().
     * */
    fun regIn(): List<Any> {
        if (dbms != SupportedDBs.NotSupported) {
            val result = mutableListOf<Any>()
            result.add("'${dbms.identity}'")
            result.add(if (this.alias.isBlank()) "'${this.dbms} - ${this.database}'" else "'${this.alias}'")
            result.add(if (user == "null") "null" else "'$user'")
            result.add(if (password == "null") "null" else "'$password'")
            result.add("'$database'")
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
        if (dbms != other.dbms) return false
        if (alias != other.alias) return false
        if (user != other.user) return false
        if (password != other.password) return false
        if (database != other.database) return false
        if (description != other.description) return false
        if (url != other.url) return false
        if (protected != other.protected) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + dbms.hashCode()
        result = 31 * result + alias.hashCode()
        result = 31 * result + user.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + database.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + protected.hashCode()
        return result
    }

    override fun toString(): String {
        return "RegRow(id=$id, dbms=$dbms, alias='$alias', user='$user', " +
                "password='$password', database='$database', description='$description', " +
                "url='$url', protected=$protected)"
    }
}

/**
 * Use this to parse single row of registration table.
 * @return Data class, RegRow
 * */
fun List<Any>.regOut(): RegRow {
    if (this.size != 9) throw ConvertError("List.regOut said: This list's size not equals to 9.")
    if (this[1] !in listOf(SupportedDBs.SQLite.identity, SupportedDBs.PostgreSQL.identity)) {
        throw ConvertError("List.regOut said: Database not supported.")
    }
    if (this[8] !in listOf(0, 1)) throw ConvertError("List.regOut said: RegRow_attr_protected set failed.")

    return RegRow(
        id = this[0] as Int,
        dbms = when (this[1] as String) {
            "Sqlite" -> SupportedDBs.SQLite
            "Postgresql" -> SupportedDBs.PostgreSQL
            else -> throw ConvertError("List.regOut attr_db said: Database not supported.")
        },
        alias = this[2] as String,
        user = this[3] as String,
        password = this[4] as String,
        database = this[5] as String,
        description = this[6] as String,
        url = this[7] as String,
        protected = this[8] as Int != 0
    )
}

