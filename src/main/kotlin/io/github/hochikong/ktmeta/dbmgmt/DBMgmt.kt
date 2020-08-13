/**
 * @author Hochikong
 * */
package io.github.hochikong.ktmeta.dbmgmt

import io.github.hochikong.ktmeta.predefined.NoDatabasesIsAvailable
import org.slf4j.LoggerFactory
import java.sql.Connection
import kotlin.properties.Delegates

object DBMgmt {
    private val loggerDBMGMT = LoggerFactory.getLogger("ktmeta->dbmgmt")
    private var regIsEmpty: Boolean = true
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

    /**
     * Return JDBC connection by database's [name]
     */
    fun getConnection(name: String): Connection {
        if (regIsEmpty) {
            val msg = "Database registration is empty."
            loggerDBMGMT.error(msg)
            throw NoDatabasesIsAvailable(msg)
        }
        TODO()
    }

}