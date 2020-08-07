/**
 * @author Hochikong
 * DB management API
 */

package io.github.hochikong.ktmeta.dbmgmt

import me.liuwj.ktorm.database.Database
import java.sql.Connection
import java.sql.DriverManager


object ManagementInterface {
    var isDriverSet: Boolean = false
        private set
    var isDBUrlSet: Boolean = false
        private set
    lateinit var config: DBConfigContainer
        private set


    /**
     * Use [driver] and [url] to create a db connection configuration.
     * */
    fun setDBConfig(driver: String, url: String): Boolean {
        require((driver == "org.sqlite.JDBC") or (driver == "org.postgresql.Driver")) { "Illegal driver name." }
        require("jdbc" in url) { "Illegal url without `jdbc`" }
        require(("sqlite" in url) or ("postgresql" in url)) { "Illegal url without `sqlite` or `postgresql`" }
        config = DBConfigContainer(driver, url)
        isDriverSet = true
        isDBUrlSet = true
        return true
    }

    /**
     * Return JDBC connection by driver and url. Use setDBConfig() before this API.
     * */
    fun getJDBCConnection(): Connection? {
        require(isDriverSet) { "Driver not set." }
        require(isDBUrlSet) { "Target DB not set." }
        try {
            Class.forName(config.driver)
            return DriverManager.getConnection(config.url)
        } catch (e: Exception) {
            println(e.toString())
        }
        return null
    }

    /**
     * Return Ktorm's Database. Use setDBConfig() before this API. You can provide [user] and [password] for connection.
     * */
    fun getDSLDatabase(user: String? = null, password: String? = null): Database? {
        require(isDBUrlSet) { "Target DB not set." }
        try {
            return Database.connect(
                url = config.url,
                driver = config.driver,
                user = user,
                password = password,
                dialect = config.dialect
            )
        } catch (e: Exception) {
            println(e.toString())
        }
        return null
    }
}
