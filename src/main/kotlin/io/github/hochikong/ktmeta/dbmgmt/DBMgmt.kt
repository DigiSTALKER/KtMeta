/**
 * @author Hochikong
 * */
package io.github.hochikong.ktmeta.dbmgmt

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.hochikong.ktmeta.predefined.NoDatabasesIsAvailable
import io.github.hochikong.ktmeta.predefined.NoSuchDatabaseInRegistrationTable
import me.liuwj.ktorm.database.Database
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.DriverManager
import kotlin.properties.Delegates

object DBMgmt {
    private val loggerDBMGMT = LoggerFactory.getLogger("ktmeta->dbmgmt")
    private var regIsEmpty: Boolean = true
    private val currentDatabases = mutableListOf<DBConfigContainer>()
    private var queryResult: List<List<Any>>? by Delegates.observable(listOf()) { _, _, newValue ->
        regIsEmpty = when (newValue) {
            null -> true
            else -> {
                DBRegCatalog.updateCatalog(newValue)
                false
            }
        }
    }

    init {
        regIsEmpty = if (!Maintainer.hasTable()) {
            Maintainer.createTable()
            true
        } else {
            queryResult = Maintainer.queryAllRows()
            false
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
            config.dataSourceClassName = configContainer.dataSource
            config.poolName = "ConnectionPoolOf${configContainer.name}"
            if (configContainer.username !== "null") config.username = configContainer.username
            if (configContainer.password != "null") config.password = configContainer.password

            val dataSource = HikariDataSource(config)
            addUsingDatabase(configContainer)
            return Database.connect(dataSource)
        } else {
            throw NoSuchDatabaseInRegistrationTable("DBMGMT.getConnection said: Database $name not exists.")
        }
    }
}