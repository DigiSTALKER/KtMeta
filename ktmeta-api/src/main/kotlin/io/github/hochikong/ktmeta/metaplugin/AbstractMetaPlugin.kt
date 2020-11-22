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

package io.github.hochikong.ktmeta.metaplugin

import io.github.hochikong.ktmeta.predefined.SupportedDBCon
import io.github.hochikong.ktmeta.predefined.SupportedDevices

/**
 * One plugin can use multiple databases. Single database can have multiple libraries.
 * A library contains multiple tables and tag tables for different metadata schemas.
 * */
abstract class AbstractMetaPlugin : MetadataRegistration {
    // each plugin maintain a map which contains all database connections, connection pools and devices.

    /**
     * Grant database access permission and save connection instance into dbs. And save token in tokens by database name.
     * @param connection All supported connection types in SupportedDBCon.
     * @param name Database's name.
     * @param username Raw username.
     * @param password Raw password.
     * */
    abstract fun grantDB(connection: SupportedDBCon, name: String, username: String, password: String): Boolean

    /**
     * The basic method for execute any sql. You should provide wrapper for user.
     * @param database Database in dbs.
     * @param sql Any method or data for you to run sql.
     */
    abstract fun <T, R> executeSQL(database: String, sql: T): R

    /**
     * Grant device for [path] access permission and save device instance into devices.
     * */
    abstract fun grantDevice(device: SupportedDevices, path: String): Boolean

    /**
     * Scan metadata json file's content and convert to sql DML fit for MetadataRegistration
     * */
    abstract fun scanFiles(device: String, paths: List<String>): List<String>

    /**
     * KtMeta only support metadata query in RDBMS. Full text query base on ElasticSearch.
     * Use this function to convert sql rows into json string.
     * @param sqlResult A row in any data type, e.g., plain text, data class.
     */
    abstract fun sqlToJson(sqlResult: Any): String
}