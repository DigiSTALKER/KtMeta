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
 * Supported DBs
 * */
enum class SupportedDBs(val identity: String) {
    SQLite("Sqlite"),
    PostgreSQL("Postgresql")
}


/**
 * Data class used for keeping database configurations.
 * @param driver JDBC driver name, e.g., 'org.sqlite.JDBC' or 'org.postgresql.Driver'.
 * @param url Target database url, e.g., 'jdbc:sqlite:xxxx.db'.
 * @constructor Create a new db config data class by [driver] and [url].
 */
data class DBConfigContainer(
    val driver: String,
    val url: String,
    val username: String?,
    val password: String?
) {
    val dialect: SqlDialect = when {
        driver.contains("sqlite") -> {
            SQLiteDialect()
        }
        driver.contains("postgresql") -> {
            PostgreSqlDialect()
        }
        else -> throw IllegalArgumentException("Only sqlite or postgresql is supported.")
    }

    val dataSource: String = when {
        driver.contains("sqlite") -> "org.sqlite.SQLiteDataSource"
        driver.contains("postgresql") -> "org.postgresql.ds.PGSimpleDataSource"
        else -> throw java.lang.IllegalArgumentException("Only sqlite or postgresql is supported.")
    }

    override fun equals(other: Any?): Boolean {
        if (other is DBConfigContainer) {
            return (other.driver == this.driver) and (other.url == this.url) and (other.dialect == this.dialect) and (other.dataSource == this.dataSource)
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
 * @param password When use PostgreSQL as database is String (encrypted), or null when use SQLite.
 * @param dbs Map<String, String>, e.g.,  "TestDB":"DB URL"
 *        DB URL: Simple url without username and password, e.g., "jdbc:postgresql://localhost/test".
 * @param descriptions Map<String, String>, e.g.,  "TestDB":"DB DESCRIPTION"
 *        DESCRIPTION: Description for db.
 *
 * Examples:
 *
 * JSON format when use Postgresql:
 * ```json
 * {
 *   "WhatDBYouChoose": "Postgresql",
 *   "Username": "NAME",
 *   "Password": "PASSWORD",
 *   "YouDBs": {
 *   "DBNAME1": "DB URL",
 *   "DBNAME2": "DB URL",
 *   "etc.": "etc."
 *   },
 *   "DB Descriptions":{
 *   "DBNAME1": "DESC",
 *   "DBNAME2": "DESC"
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
 *   "YouDBs": {
 *   "DBNAME1": "DB URL",
 *   "DBNAME2": "DB URL",
 *   "etc.": "etc."
 *   },
 *   "DB Descriptions":{
 *   "DBNAME1": "DESC",
 *   "DBNAME2": "DESC"
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
    @JsonProperty("YouDBs")
    val dbs: Map<String, String>,
    @JsonProperty("DB Descriptions")
    val descriptions: Map<String, String>
)