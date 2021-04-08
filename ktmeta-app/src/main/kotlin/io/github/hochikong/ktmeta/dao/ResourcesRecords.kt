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

/**
 * Metadata plugins resources
 * */
data class MPResourceRecord(
    var id: Long = 0,
    var plugin_name: String = "",
    var plugin_version: String = "",
    var plugin_class_name: String = "",
    var plugin_desc: String = "",
    var plugin_helper: String = ""
) : ResourcesRecord()

/**
 * Metadata library resources
 * */
data class MLResourceRecord(
    var id: Long = 0,
    var lib_name: String = "",
    var lib_desc: String = "",
    var assign_plugin: String = "",
    var assign_db: String = "",
    var assign_index: String = ""
) : ResourcesRecord()

