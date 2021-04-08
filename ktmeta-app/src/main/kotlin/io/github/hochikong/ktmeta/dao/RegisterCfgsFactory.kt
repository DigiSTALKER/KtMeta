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

enum class DAOTableNames(val tName: String) {
    DBResource("dbs_registration"),
    ESResource("indices_registration"),
    MLResource("metalibs_registration"),
    MPResource("metaplugins_registration");

    override fun toString(): String {
        return this.tName
    }
}

object DAOConfigFactory {
    private const val sqliteDriverName = "org.sqlite.JDBC"
    private const val sqliteUrl = "jdbc:sqlite:resources.db"

    // hikari
    private const val poolConfigPath = ".\\hikari.properties"

    // logback
    val logKey = mapOf(
        DAOTableNames.DBResource.tName to "ktmeta-dao-db",
        DAOTableNames.ESResource.tName to "ktmeta-dao-es",
        DAOTableNames.MPResource.tName to "ktmeta-dao-mp",
        DAOTableNames.MLResource.tName to "ktmeta-dao-ml"
    )

    /**
     * Return HikariConfig for DAOs, which use SQLite as its backend to register database, metadata plugins, etc.
     *
     * @param poolName String, as hikaricp's connection pool's name.
     * */
    fun buildSqliteCPConfig(poolName: String): HikariConfig {
        val config = HikariConfig(poolConfigPath)
        config.jdbcUrl = sqliteUrl
        config.poolName = "ConnectionPoolOf$poolName"
        config.maximumPoolSize = 2
        return config
    }

    /**
     * Return HikariConfig for DAOs, which use PostgreSQL as its backend to register database, metadata plugins, etc.
     *
     * @param poolName String, as hikaricp's connection pool's name.
     * */
    fun buildPostgreCPConfig(poolName: String): HikariConfig {
        throw NotImplementedError("Not implemented yet.")
    }
}