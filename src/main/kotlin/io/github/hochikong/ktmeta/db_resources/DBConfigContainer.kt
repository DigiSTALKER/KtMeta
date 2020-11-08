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
 * This is container file, contains classes and data classes
 */
package io.github.hochikong.ktmeta.db_resources

import io.github.hochikong.ktmeta.predefined.ConvertError
import io.github.hochikong.ktmeta.predefined.SupportedDBs
import me.liuwj.ktorm.database.SqlDialect
import me.liuwj.ktorm.support.postgresql.PostgreSqlDialect
import me.liuwj.ktorm.support.sqlite.SQLiteDialect


/**
 * Data class used for keeping database configurations.
 *
 * DDL for reference:
 * ```SQL
 * CREATE TABLE dbs_registration(
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
 */
data class DBConfigContainer(
    val type: SupportedDBs,
    val name: String,
    val desc: String,
    val url: String,
    val username: String,
    val password: String
) {
    val protected = password != "null"
    val dialect: SqlDialect = when (type) {
        SupportedDBs.SQLite -> {
            SQLiteDialect()
        }
        SupportedDBs.PostgreSQL -> {
            PostgreSqlDialect()
        }
        SupportedDBs.NotSupported -> {
            throw ConvertError("DBConfigContainer -> attr_dialect said: This database not supported.")
        }
    }

    val dataSource: String = when (type) {
        SupportedDBs.PostgreSQL -> SupportedDBs.PostgreSQL.dataSource
        SupportedDBs.SQLite -> SupportedDBs.SQLite.dataSource
        SupportedDBs.NotSupported -> throw ConvertError("DBConfigContainer -> attr_dataSource said: This database not supported")
    }
    val jdbcDriver: String = type.jdbcDriver
}

val RESIGTDB_COLUMN = listOf("id", "dbms", "alias", "user", "password", "database", "description", "url", "protected")