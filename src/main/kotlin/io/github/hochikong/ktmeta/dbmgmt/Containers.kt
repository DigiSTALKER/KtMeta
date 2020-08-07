/**
 * @author Hochikong
 * This is container file, contains classes and data classes
 */
package io.github.hochikong.ktmeta.dbmgmt

import com.fasterxml.jackson.annotation.JsonProperty
import me.liuwj.ktorm.database.SqlDialect
import me.liuwj.ktorm.support.postgresql.PostgreSqlDialect
import me.liuwj.ktorm.support.sqlite.SQLiteDialect

/**
 * Data class used for keeping database configurations.
 * @param driver JDBC driver name, e.g., 'org.sqlite.JDBC' or 'org.postgresql.Driver'.
 * @param url Target database url, e.g., 'jdbc:sqlite:xxxx.db'.
 * @constructor Create a new db config data class by [driver] and [url].
 */
data class DBConfigContainer(val driver: String, val url: String) {
    val dialect: SqlDialect = when {
        "sqlite" in driver -> {
            SQLiteDialect()
        }
        "postgresql" in driver -> {
            PostgreSqlDialect()
        }
        else -> {
            SQLiteDialect()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is DBConfigContainer) {
            return (other.driver == this.driver) and (other.url == this.url) and (other.dialect == this.dialect)
        }
        return false
    }

    override fun hashCode(): Int {
        var result = driver.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + dialect.hashCode()
        return result
    }
}


/**
 * Data class used for database registration, serialization and deserialization.
 *
 * @param db String, only "Sqlite" and "Postgresql" is legal.
 * @param username When use PostgreSQL as database is String, or null when use SQLite.
 * @param password When use PostgreSQL as database is String, or null when use SQLite.
 * @param encrypted When use PostgreSQL as database is true, or false when use SQLite.
 * @param dbs
 *
 * Examples:
 *
 * JSON format when use Postgresql:
 * ```json
 * {
 *   "WhatDBYouChoose": "Postgresql",
 *   "Username": "NAME",
 *   "Password": "PASSWORD",
 *   "isEncrypted": true,
 *   "YouDBs": {
 *   "DBNAME1": "DESCRIPTION",
 *   "DBNAME2": "DESCRIPTION",
 *   "etc.": "etc."
 *   }
 *  }
 *  ```
 *
 *  JSON format when use Sqlite (without password protection)
 *  ```json
 * {
 *   "WhatDBYouChoose": "Sqlite",
 *   "Username": null,
 *   "Password": null,
 *   "isEncrypted": false,
 *   "YouDBs": {
 *   "DBNAME1": "DESCRIPTION",
 *   "DBNAME2": "DESCRIPTION",
 *   "etc.": "etc."
 *   }
 *  }
 *  ```
 */
data class DBRegJSONContainer(
    @JsonProperty("WhatDBYouChoose")
    val db: String,
    @JsonProperty("Username")
    val username: String?,
    @JsonProperty("Password")
    val password: String?,
    @JsonProperty("isEncrypted")
    val encrypted: Boolean,
    @JsonProperty("YouDBs")
    val dbs: Map<String, String>
)
