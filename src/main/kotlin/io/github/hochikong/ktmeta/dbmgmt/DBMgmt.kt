/*
 * Copyright 2020 Hochikong
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @author Hochikong
 * */
package io.github.hochikong.ktmeta.dbmgmt

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.hochikong.ktmeta.predefined.NoDatabasesIsAvailable
import io.github.hochikong.ktmeta.predefined.NoSuchDatabaseInRegistrationTable
import io.github.hochikong.ktmeta.predefined.SupportedDBs
import me.liuwj.ktorm.database.Database
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.DriverManager
import kotlin.properties.Delegates

object DBMgmt {
    private val loggerDBMGMT = LoggerFactory.getLogger("ktmeta->dbmgmt")
    var regIsEmpty: Boolean = true
        private set
    val currentDatabases = mutableListOf<DBConfigContainer>()
    private var queryResult: List<List<Any>>? by Delegates.observable(listOf()) { _, _, newValue ->
        regIsEmpty = when {
            newValue == null -> true
            newValue.isEmpty() -> true
            else -> {
                DBRegCatalog.updateCatalog(newValue)
                false
            }
        }
    }

    init {
        if (!Maintainer.hasTable()) {
            Maintainer.createTable()
        } else {
            queryResult = Maintainer.queryAllRows()
        }
    }


    private fun checkRegIsEmpty(name: String): RegRow? {
        if (regIsEmpty) {
            val msg = "DBMGMT.getConnection said: Database registration is empty."
            loggerDBMGMT.error(msg)
            throw NoDatabasesIsAvailable(msg)
        }
        return DBRegCatalog[name]
    }

    private fun addUsingDatabase(config: DBConfigContainer) {
        currentDatabases.add(config)
        loggerDBMGMT.info("Create new jdbc connection/database/connection pool for url ${config.name}")
        loggerDBMGMT.info(
            "Currently using databases: ${currentDatabases.map { it.name }}" +
                    "@${currentDatabases.map { it.url }} "
        )
    }


    // APIs
    /**
     * Manually query and update database's catalog.
     * */
    fun queryReg(): Boolean {
        queryResult = Maintainer.queryAllRows()
        return true
    }

    /**
     * Check database's catalog.
     * @return list, ("catalog", empty or not(Boolean), all available databases(set))
     * */
    fun checkCatalog(): List<Any> {
        return if (regIsEmpty) {
            listOf("catalog", true, DBRegCatalog.keys())
        } else {
            listOf("catalog", false, DBRegCatalog.keys())
        }
    }

    /**
     * Add database configuration.
     * */
    fun addDatabase(
        type: SupportedDBs,
        name: String,
        desc: String,
        user: String,
        password: String,
        url: String
    ): Boolean {
        val tmp = RegRow(
            id = -1,
            db = type,
            user = user,
            password = password,
            name = name,
            description = desc,
            url = url,
            protected = password != "null"
        )
        if (Maintainer.insertRow(tmp.regIn())) {
            queryReg()
            return true
        }
        return false
    }

    /**
     * Delete database configuration.
     * */
    fun removeDatabase(name: String): Boolean {
        return if (checkRegIsEmpty(name) != null) {
            Maintainer.deleteRow("name == $'$name'")
            queryReg()
        } else {
            false
        }
    }

    /**
     * Return JDBC connection by database's [name]
     */
    fun getConnection(name: String): Connection {
        val configContainer: DBConfigContainer
        val row: RegRow? = checkRegIsEmpty(name)
        if (row != null) {
            configContainer = DBConfigContainer(
                type = row.db,
                name = name,
                desc = row.description,
                url = row.url,
                username = row.user,
                password = row.password
            )
            val con = if (configContainer.username != "null" && configContainer.password != "null") {
                DriverManager.getConnection(
                    configContainer.url,
                    configContainer.username,
                    configContainer.password
                )
            } else {
                DriverManager.getConnection(configContainer.url)
            }
            addUsingDatabase(configContainer)
            return con
        } else {
            throw NoSuchDatabaseInRegistrationTable("DBMGMT.getConnection said: Database $name not exists.")
        }
    }

    /**
     * Return single Ktorm's database by database's [name]
     * */
    fun getDatabase(name: String): Database {
        val configContainer: DBConfigContainer
        val row = checkRegIsEmpty(name)
        if (row != null) {
            configContainer = DBConfigContainer(
                type = row.db,
                name = name,
                desc = row.description,
                url = row.url,
                username = row.user,
                password = row.password
            )
            val database = Database.connect(
                url = configContainer.url,
                driver = configContainer.jdbcDriver,
                user = if (configContainer.username == "null") null else configContainer.username,
                password = if (configContainer.password == "null") null else configContainer.password,
                dialect = configContainer.dialect
            )
            addUsingDatabase(configContainer)
            return database
        } else {
            throw NoSuchDatabaseInRegistrationTable("DBMGMT.getConnection said: Database $name not exists.")
        }
    }

    /**
     * Return HikariCP connection pool by database's [name]
     * */
    fun getPool(name: String, configPath: String = ".\\hikari.properties"): Database {
        val configContainer: DBConfigContainer
        val row = checkRegIsEmpty(name)
        if (row != null) {
            configContainer = DBConfigContainer(
                type = row.db,
                name = name,
                desc = row.description,
                url = row.url,
                username = row.user,
                password = row.password
            )
            val config = HikariConfig(configPath)
            config.jdbcUrl = configContainer.url
            // config.dataSourceClassName = configContainer.dataSource
            config.poolName = "ConnectionPoolOf${configContainer.name}"
            if (configContainer.username !== "null") config.username = configContainer.username
            if (configContainer.password != "null") config.password = configContainer.password

            val dataSource = HikariDataSource(config)
            addUsingDatabase(configContainer)
            return Database.connect(dataSource, configContainer.dialect)
        } else {
            throw NoSuchDatabaseInRegistrationTable("DBMGMT.getConnection said: Database $name not exists.")
        }
    }
}