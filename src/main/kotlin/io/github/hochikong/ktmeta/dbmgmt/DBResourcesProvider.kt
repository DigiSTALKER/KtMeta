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
 * */
package io.github.hochikong.ktmeta.dbmgmt

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.hochikong.ktmeta.predefined.Encryption
import io.github.hochikong.ktmeta.predefined.NoDatabasesIsAvailable
import io.github.hochikong.ktmeta.predefined.NoSuchDatabaseInRegistrationTable
import io.github.hochikong.ktmeta.predefined.SupportedDBs
import me.liuwj.ktorm.database.Database
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.DriverManager
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

object DBResourcesProvider {
    private data class Token(val username: String, val password: String) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Token

            if (username != other.username) return false
            if (password != other.password) return false

            return true
        }

        override fun hashCode(): Int {
            var result = username.hashCode()
            result = 31 * result + password.hashCode()
            return result
        }
    }


    private val securityKey = UUID.nameUUIDFromBytes("${System.currentTimeMillis()}".toByteArray()).toString()

    private val loggerDBMGMT = LoggerFactory.getLogger("ktmeta->dbmgmt")

    var regIsEmpty: Boolean = true
        private set

//    val currentDatabases = mutableListOf<DBConfigContainer>()

    private var queryResult: List<List<Any>>? by Delegates.observable(listOf()) { _, _, newValue ->
        regIsEmpty = when {
            newValue == null -> {
                DBRegCatalog.updateCatalog(listOf())
                true
            }
            newValue.isEmpty() -> {
                DBRegCatalog.updateCatalog(listOf())
                true
            }
            else -> {
                DBRegCatalog.updateCatalog(newValue)
                false
            }
        }
    }

    private val tokenCache: Cache<String, Token> = CacheBuilder
        .newBuilder()
        .expireAfterWrite(4L, TimeUnit.HOURS)
        .build()

    init {
        if (!DBMaintainer.hasTable()) {
            DBMaintainer.createTable()
        } else {
            queryResult = DBMaintainer.queryAllRows()
        }
    }


    private fun checkRegIsEmpty(database: String): RegRow? {
        queryReg()
        if (regIsEmpty) {
            val msg = "DBMGMT.getConnection said: Database db_registration is empty."
            loggerDBMGMT.error(msg)
            throw NoDatabasesIsAvailable(msg)
        }
        return DBRegCatalog[database]
    }

    /*private fun addUsingDatabase(config: DBConfigContainer) {
        currentDatabases.add(config)
        loggerDBMGMT.info("Create new jdbc connection/database/connection pool for url ${config.name}")
        loggerDBMGMT.info(
            "Currently using databases: ${currentDatabases.map { it.name }}" +
                    "@${currentDatabases.map { it.url }} "
        )
    }*/


    // APIs
    /**
     * Manually query and update database's catalog by delegate.
     * */
    fun queryReg(): Boolean {
        queryResult = DBMaintainer.queryAllRows()
        return true
    }

    /**
     * Check database's catalog. Find out all database's name.
     * @return list, ("catalog", empty or not(Boolean), all available databases(set))
     * */
    fun checkCatalog(): List<String> {
        queryReg()
        return DBRegCatalog.keys().toList()
    }

    /**
     * Add database configuration.
     * @param user A string after encryption or "null".
     * @param password A string after encryption or "null".
     * */
    fun addDatabase(
        type: SupportedDBs,
        alias: String,
        user: String,
        password: String,
        database: String,
        desc: String,
        url: String
    ): Boolean {
        if (type == SupportedDBs.NotSupported) return false

        val tmp = RegRow(
            id = -1,
            dbms = type,
            alias = alias,
            user = user,
            password = password,
            database = database,
            description = desc,
            url = url,
            protected = password != "null"
        )
        if (DBMaintainer.insertRow(tmp.regIn())) {
            queryReg()
            return true
        }
        return false
    }

    /**
     * Delete database configuration.
     * */
    fun removeDatabase(database: String, token: String): Boolean {
        return if (tokenCache.getIfPresent(token) != null) {
            if (checkRegIsEmpty(database) != null) {
                DBMaintainer.deleteRow("name == '$database'")
                queryReg()
            } else {
                false
            }
        } else {
            false
        }
    }

    /**
     * Grant database access permission.
     * @param database Database name.
     * @param username Raw username.
     * @param password Raw password.
     * */
    fun grantDatabase(database: String, username: String, password: String): String {
        val row: RegRow? = checkRegIsEmpty(database)
        if (row != null) {
            val usernamePass: Boolean = when {
                (username == row.user) && (username == "null") -> true
                else -> Encryption.verify(username, row.user)
            }
            val passwordPass: Boolean = when {
                (password == row.password) && (password == "null") -> true
                else -> Encryption.verify(password, row.password)
            }
            if (usernamePass && passwordPass) {
                val token = Encryption.encrypt("$username$password$securityKey")
                tokenCache.put(token, Token(username, password))
                return token
            }

        }
        throw NoSuchDatabaseInRegistrationTable("Your password or username is error.")
    }

    /**
     * Get a token from tokenCache.
     * */
    private fun verifyToken(token: String): Token? {
        return tokenCache.getIfPresent(token)
    }

    /**
     * Verify a token and return ConfigContainer.
     * */
    private fun getConfigContainer(
        token: String,
        queryRow: RegRow,
        database: String
    ): DBConfigContainer {
        var realUsername = ""
        var realPassword = ""
        val gToken = verifyToken(token)
        if (gToken != null) {
            realUsername = gToken.username
            realPassword = gToken.password
        }

        if (realUsername.isBlank()) throw IllegalStateException("You should grantDatabase() first.")
        if (realPassword.isBlank()) throw IllegalStateException("You should grantDatabase() first.")

        return DBConfigContainer(
            type = queryRow.dbms,
            name = database,
            desc = queryRow.description,
            url = queryRow.url,
            username = realUsername,
            password = realPassword
        )
    }

    /**
     * Return JDBC connection by database's [database]
     */
    fun getConnection(database: String, token: String): Connection {
        val configContainer: DBConfigContainer
        val queryRow: RegRow? = checkRegIsEmpty(database)
        if (queryRow != null) {
            configContainer = getConfigContainer(token, queryRow, database)
            return if (configContainer.username != "null" && configContainer.password != "null") {
                DriverManager.getConnection(
                    configContainer.url,
                    configContainer.username,
                    configContainer.password
                )
            } else {
                DriverManager.getConnection(configContainer.url)
            }
        } else {
            throw NoSuchDatabaseInRegistrationTable("DBMGMT.getConnection said: Database $database not exists.")
        }
    }

    /**
     * Return single Ktorm's database by database's [database]
     * */
    fun getDatabase(database: String, token: String): Database {
        val configContainer: DBConfigContainer
        val queryRow = checkRegIsEmpty(database)
        if (queryRow != null) {
            configContainer = getConfigContainer(token, queryRow, database)
            return Database.connect(
                url = configContainer.url,
                driver = configContainer.jdbcDriver,
                user = if (configContainer.username == "null") null else configContainer.username,
                password = if (configContainer.password == "null") null else configContainer.password,
                dialect = configContainer.dialect
            )
        } else {
            throw NoSuchDatabaseInRegistrationTable("DBMGMT.getConnection said: Database $database not exists.")
        }
    }

    /**
     * Return HikariCP connection pool by database's [database]
     * */
    fun getPool(database: String, token: String, configPath: String = ".\\hikari.properties"): Database {
        val configContainer: DBConfigContainer
        val queryRow = checkRegIsEmpty(database)
        if (queryRow != null) {
            configContainer = getConfigContainer(token, queryRow, database)
            val config = HikariConfig(configPath)
            config.jdbcUrl = configContainer.url
            // config.dataSourceClassName = configContainer.dataSource
            config.poolName = "ConnectionPoolOf${configContainer.name}"
            if (configContainer.username !== "null") config.username = configContainer.username
            if (configContainer.password != "null") config.password = configContainer.password

            val dataSource = HikariDataSource(config)
            return Database.connect(dataSource, configContainer.dialect)
        } else {
            throw NoSuchDatabaseInRegistrationTable("DBMGMT.getConnection said: Database $database not exists.")
        }
    }
}