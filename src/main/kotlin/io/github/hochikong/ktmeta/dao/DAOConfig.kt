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

package io.github.hochikong.ktmeta.dao

import com.zaxxer.hikari.HikariConfig

object DAOConfig {
    private const val driverName = "org.sqlite.JDBC"
    private const val dbURL = "jdbc:sqlite:resources.db"
    private const val poolConfigPath = ".\\hikari.properties"
    val logKey = mapOf(
        DBRecord.tableName to "ktmeta-dao-db",
        ESRecord.tableName to "ktmeta-dao-es",
        MPRecord.tableName to "ktmeta-dao-mp",
        MLRecord.tableName to "ktmeta-dao-ml"
    )

    fun buildCPConfig(poolName: String): HikariConfig {
        val config = HikariConfig(poolConfigPath)
        config.jdbcUrl = dbURL
        config.poolName = "ConnectionPoolOf$poolName"
        config.maximumPoolSize = 2
        return config
    }
}