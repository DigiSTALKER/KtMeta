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

/**
 * @author Hochikong
 * Pre-defined Data
 * */
package io.github.hochikong.ktmeta.predefined

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule


/**
 * Pre-defined supported DBs.
 * @param identity String, used by others module in ktmeta.
 * @param jdbcDriver String, used as JDBC driver name -> Class.forName(driver).
 * @param dataSource String, used by HikariCP as datasource class name.
 * */
enum class SupportedDBs(val identity: String, val jdbcDriver: String, val dataSource: String) {
    SQLite("Sqlite", "org.sqlite.JDBC", "org.sqlite.SQLiteDataSource"),
    PostgreSQL("Postgresql", "org.postgresql.Driver", "org.postgresql.ds.PGSimpleDataSource"),
    NotSupported("", "", "");

    override fun toString(): String {
        return this.identity
    }
}

/**
 * Pre-defined exceptions.
 * */
class NoDatabasesIsAvailable(msg: String) : Exception(msg)
class ConvertError(msg: String) : Exception(msg)
class NoSuchDatabaseInRegistrationTable(msg: String) : Exception(msg)
class InitialDeviceFailed(msg: String) : Exception(msg)

/**
 * Pre-defined file type.
 * */
enum class FileType(val identity: String) {
    File("File"),
    Directory("Directory")
}


/**
 * Pre-defined file size limit.
 * */
enum class SizeLimit {
    Normal,
    TooBig
}


/**
 * Pre-defined devices support
 * */
enum class Devices(val identity: String, val className: String) {
    LocalDevice("LocalDiskDriver", "io.github.hochikong.ktmeta.device.driver.LocalDiskDriver")
}

/**
 * Universal JSON Mapper
 * */
val JSONMapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())

/**
 * Used by each executeMgmtTask
 * */
data class ResultMsg(val succeeded: Boolean, val result: Any, val msg: String)