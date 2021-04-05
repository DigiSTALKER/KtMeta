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

import me.liuwj.ktorm.schema.Table
import me.liuwj.ktorm.schema.int
import me.liuwj.ktorm.schema.text
import me.liuwj.ktorm.schema.varchar

sealed class ResourcesRecord


/**
 * Database resources.
 *
 * Password must be encrypted.
 * */
data class DBResourceRecord(
    var id: Long = 0,
    var db_type: String = "",
    var db_name: String = "",
    var db_desc: String = "",
    var db_url: String = "",
    var user: String = "",
    var password: String = "",
    var save_passwd: Int = 0
) : ResourcesRecord()


/**
 * Elastic Search resources.
 * */
data class ESResourceRecord(
    var id: Long = 0,
    var index_name: String = "",
    var index_desc: String = "",
    var index_url: String = ""
) : ResourcesRecord()

/*
CREATE TABLE IF NOT EXISTS metaplugins_registration(
id INTEGER PRIMARY KEY AUTOINCREMENT ,
plugin_name TEXT NOT NULL UNIQUE ,
plugin_version TEXT NOT NULL UNIQUE ,
plugin_class_name TEXT NOT NULL ,
plugin_desc TEXT NOT NULL ,
plugin_helper TEXT NOT NULL
);
*/
object MPRegTable : Table<Nothing>(MPRecord.tableName) {
    val id = int("id").primaryKey()
    val name = varchar("plugin_name")
    val version = varchar("plugin_version")
    val cname = varchar("plugin_class_name")
    val desc = text("plugin_desc")
    val helper = text("plugin_helper")
}

data class MPRecord(
    val id: Int = -1,
    val plugin_name: String,
    val plugin_version: String,
    val plugin_class_name: String,
    val plugin_desc: String,
    val plugin_helper: String
) : ResourcesRecord() {
    companion object {
        const val tableName = "metaplugins_registration"
        val ddl = """
            
        """.trimIndent()
    }
}

/*
CREATE TABLE IF NOT EXISTS metalibs_registration
(
id       INTEGER PRIMARY KEY AUTOINCREMENT,
lib_name TEXT                                                    NOT NULL UNIQUE,
lib_desc TEXT                                                    NOT NULL,
assign_plugin REFERENCES metaplugins_registration (plugin_name) NOT NULL,
assign_db REFERENCES dbs_registration (database) UNIQUE,
assign_index REFERENCES indices_registration (index_name) UNIQUE
);
*/
object MLRegTable : Table<Nothing>(MLRecord.tableName) {
    val id = int("id").primaryKey()
    val name = varchar("lib_name")
    val desc = text("lib_desc")
    val plugin = varchar("assign_plugin")
    val db = varchar("assign_db")
    val index = varchar("assign_index")
}

data class MLRecord(
    val id: Int = -1,
    val lib_name: String,
    val lib_desc: String,
    val assign_plugin: String,
    val assign_db: String,
    val assign_index: String
) : ResourcesRecord() {
    companion object {
        const val tableName = "metalibs_registration"
        val ddl = """
            
        """.trimIndent()
    }
}

