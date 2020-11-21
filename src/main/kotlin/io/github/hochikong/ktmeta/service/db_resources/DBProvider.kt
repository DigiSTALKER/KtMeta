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

package io.github.hochikong.ktmeta.service.db_resources

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.hochikong.ktmeta.dao.DAOConfig
import io.github.hochikong.ktmeta.dao.DBRecord
import io.github.hochikong.ktmeta.dao.impl.DBResourceDAO
import io.github.hochikong.ktmeta.predefined.Encryption
import me.liuwj.ktorm.database.Database
import javax.sql.DataSource


/**
 * Provider of databases' connections.
 * Use this Object to retrieve connection: JDBC connection or Ktorm Database
 * */
object DBProvider{
    fun getDatabase(db: String, user: String?=null, password: String?=null): Database? {
        val ds = getDataSource(db, user, password)
        return if (ds != null) {
            Database.connect(dataSource = ds)
        } else {
            null
        }
    }

    fun getDataSource(db: String, user: String?=null, password: String?=null): DataSource? {
        val query = DBResourceDAO.getRecordByName(db)
        if (query is DBRecord) {
            val config = HikariConfig(DAOConfig.poolConfigPath)
            config.poolName = "ConnectionPoolOf${query.db_name}"
            config.jdbcUrl = query.url

            // username, password must be encrypted or simply "null"
            if (user is String) {
                if (query.user !== "null" && Encryption.verify(user, query.user)) {
                    config.username = user
                }
            }
            if (password is String) {
                if (query.password != "null" && Encryption.verify(password, query.password))
                    config.password = password
            }
            return HikariDataSource(config)
        } else {
            return null
        }
    }
}