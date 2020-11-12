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

/*
CREATE TABLE IF NOT EXISTS dbs_registration
(
id       INTEGER PRIMARY KEY AUTOINCREMENT,
dbms     TEXT NOT NULL,
database TEXT NOT NULL UNIQUE,
desc     TEXT NOT NULL,
url      TEXT NOT NULL UNIQUE,
user     TEXT,
password TEXT,
CONSTRAINT db_type_check CHECK ( dbms IN ('Sqlite', 'Postgresql') )
);
*/
object DBRegTable : Table<Nothing>(DBRecord.tableName) {
    val id = int("id").primaryKey()
    val dbms = varchar("dbms")
    val name = varchar("database")
    val desc = text("desc")
    val url = varchar("url")
    val user = varchar("user")
    val password = varchar("password")
}

data class DBRecord(
    val id: Int = -1,
    val dbms: String,
    val name: String,
    val desc: String,
    val url: String,
    val user: String,
    val password: String
) : ResourcesRecord() {
    companion object {
        const val tableName = "dbs_registration"
        val ddl = """
            CREATE TABLE IF NOT EXISTS dbs_registration
            (
                id       INTEGER PRIMARY KEY AUTOINCREMENT,
                dbms     TEXT NOT NULL,
                database TEXT NOT NULL UNIQUE,
                desc     TEXT NOT NULL,
                url      TEXT NOT NULL UNIQUE,
                user     TEXT,
                password TEXT,
                CONSTRAINT db_type_check CHECK ( dbms IN ('Sqlite', 'Postgresql') )
            );
        """.trimIndent()
    }
}

/*
CREATE TABLE IF NOT EXISTS indices_registration(
id INTEGER PRIMARY KEY AUTOINCREMENT ,
index_name TEXT NOT NULL UNIQUE ,
index_desc TEXT NOT NULL ,
index_url TEXT NOT NULL UNIQUE
);
*/
object ESRegTable : Table<Nothing>(ESRecord.tableName) {
    val id = int("id").primaryKey()
    val name = varchar("index_name")
    val desc = text("index_desc")
    val url = text("index_url")
}

data class ESRecord(
    val id: Int = -1,
    val index_name: String,
    val index_desc: String,
    val index_url: String
) : ResourcesRecord() {
    companion object {
        const val tableName = "indices_registration"
        val ddl = """
            CREATE TABLE IF NOT EXISTS indices_registration
            (
                id         INTEGER PRIMARY KEY AUTOINCREMENT,
                index_name TEXT NOT NULL UNIQUE,
                index_desc TEXT NOT NULL,
                index_url  TEXT NOT NULL UNIQUE
            );
        """.trimIndent()
    }
}

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
            CREATE TABLE IF NOT EXISTS metaplugins_registration
            (
                id                INTEGER PRIMARY KEY AUTOINCREMENT,
                plugin_name       TEXT NOT NULL UNIQUE,
                plugin_version    TEXT NOT NULL UNIQUE,
                plugin_class_name TEXT NOT NULL,
                plugin_desc       TEXT NOT NULL,
                plugin_helper     TEXT NOT NULL
            );
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
            CREATE TABLE IF NOT EXISTS metalibs_registration
            (
                id       INTEGER PRIMARY KEY AUTOINCREMENT,
                lib_name TEXT                                                    NOT NULL UNIQUE,
                lib_desc TEXT                                                    NOT NULL,
                assign_plugin REFERENCES meta_plugins_registration (plugin_name) NOT NULL,
                assign_db REFERENCES dbs_registration (database) UNIQUE,
                assign_index REFERENCES indices_registration (index_name) UNIQUE
            );
        """.trimIndent()
    }
}

