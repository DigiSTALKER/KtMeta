/**
 * @author Hochikong
 * This is container file, contains classes and data classes
 */
package io.github.hochikong.ktmeta.dbmgmt

import io.github.hochikong.ktmeta.predefine.SupportedDBs
import me.liuwj.ktorm.database.SqlDialect
import me.liuwj.ktorm.support.postgresql.PostgreSqlDialect
import me.liuwj.ktorm.support.sqlite.SQLiteDialect


/**
 * Data class used for keeping database configurations.
 *
 * DDL for reference:
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
 */
data class DBConfigContainer(
    val type: SupportedDBs,
    val name: String,
    val desc: String,
    val url: String,
    val username: String?,
    val password: String?
) {
    val protected = username != null
    val dialect: SqlDialect = when (type) {
        SupportedDBs.SQLite -> {
            SQLiteDialect()
        }
        SupportedDBs.PostgreSQL -> {
            PostgreSqlDialect()
        }
    }

    val dataSource: String = when (type) {
        SupportedDBs.PostgreSQL -> SupportedDBs.PostgreSQL.dataSource
        SupportedDBs.SQLite -> SupportedDBs.SQLite.dataSource
    }
}