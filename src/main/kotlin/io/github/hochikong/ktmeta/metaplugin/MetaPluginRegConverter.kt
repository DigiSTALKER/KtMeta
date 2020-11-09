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

/*CREATE TABLE IF NOT EXISTS metaplugins_registration(
id INTEGER PRIMARY KEY AUTOINCREMENT ,
plugin_name TEXT NOT NULL UNIQUE ,
plugin_version TEXT NOT NULL UNIQUE ,
plugin_class_name TEXT NOT NULL ,
plugin_desc TEXT NOT NULL ,
plugin_helper TEXT NOT NULL
);*/
data class MPRegRow(
    val id: Int,
    val plugin_name: String,
    val plugin_version: String,
    val plugin_class_name: String,
    val plugin_desc: String,
    val plugin_helper: String
) {
    companion object {
        val columnNames = listOf(
            "id",
            "plugin_name",
            "plugin_version",
            "plugin_class_name",
            "plugin_desc",
            "plugin_helper"
        )
    }
}