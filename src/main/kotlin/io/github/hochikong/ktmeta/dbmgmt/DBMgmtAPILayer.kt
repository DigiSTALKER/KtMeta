/**
 * @author Hochikong
 * DB management API
 */

package io.github.hochikong.ktmeta.dbmgmt

import com.zaxxer.hikari.HikariConfig
import me.liuwj.ktorm.database.Database
import java.sql.Connection
import java.sql.DriverManager


object ManagementInterface {
    var isDriverSet: Boolean = false
        private set
    var isDBUrlSet: Boolean = false
        private set
    lateinit var dbconfig: DBConfigContainer
        private set

    /*
    * HikariCP configurations
    * */
    lateinit var poolConfig: HikariConfig
        private set


    /**
     * Use [driver] and [url] to create a db connection configuration.
     * */
    fun setDBConfig(driver: String, url: String, username: String? = null, passwd: String? = null): Boolean {
        require((driver == "org.sqlite.JDBC") or (driver == "org.postgresql.Driver")) { "Illegal driver name." }
        require("jdbc" in url) { "Illegal url without `jdbc`" }
        require(("sqlite" in url) or ("postgresql" in url)) { "Illegal url without `sqlite` or `postgresql`" }
        dbconfig = DBConfigContainer(driver, url, username, passwd)
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
            Class.forName(dbconfig.driver)
            return DriverManager.getConnection(dbconfig.url)
        } catch (e: Exception) {
            println(e.toString())
        }
        return null
    }

    /**
     * Return Ktorm's Database. Use setDBConfig() before this API. You can provide [user] and [password] for connection.
     * */
    fun getDatabase(user: String? = null, password: String? = null): Database? {
        require(isDBUrlSet) { "Target DB not set." }
        try {
            return Database.connect(
                url = dbconfig.url,
                driver = dbconfig.driver,
                user = user,
                password = password,
                dialect = dbconfig.dialect
            )
        } catch (e: Exception) {
            println(e.toString())
        }
        return null
    }

    /**
     * Return Ktorm's Database with connection pool. Use setDBConfig() before this API.
     * You can provide [user] and [passwd] for connection.
     * */
//    fun getPoolDatabase(user: String? = null, passwd: String? = null): Database?{
//        TODO()
//    }
}
