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

package io.github.hochikong.ktmeta.service.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.hochikong.ktmeta.common.DatabaseNotFoundException
import io.github.hochikong.ktmeta.common.Encryption
import io.github.hochikong.ktmeta.dao.impl.DBResourcePool
import javax.sql.DataSource


/**
 * Provider of databases' connections.
 * Use this Object to retrieve connection: datasource
 *
 * If your databases not protected by username and password (Sqlite), use a string 'null' as their values
 *
 * @throws DatabaseNotFoundException
 * */
object DBProvider {
    fun getDataSource(dbName: String, user: String, password: String): DataSource {
        val query = DBResourcePool.getRecordByName(dbName)
        if (query.id != -1L) {
            val config = HikariConfig()
            config.poolName = "ConnectionPoolOf${query.db_name}"
            config.jdbcUrl = query.db_url

            // password must be encrypted or simply "null"
            if (query.user !== "null") {
                config.username = user
            }
            if (query.password != "null" && Encryption.verify(password, query.password))
                config.password = password

            return HikariDataSource(config)
        } else {
            throw DatabaseNotFoundException("Database $dbName not found in db resources.")
        }
    }
}