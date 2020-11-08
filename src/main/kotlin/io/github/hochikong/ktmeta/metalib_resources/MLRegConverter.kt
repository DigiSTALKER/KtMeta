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

package io.github.hochikong.ktmeta.metalib_resources

/*CREATE TABLE IF NOT EXISTS metalibs
(
id   INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL UNIQUE ,
desc TEXT NOT NULL ,
assign_plugin REFERENCES plugins (name) ,
assign_db REFERENCES dbs (name) UNIQUE ,
assign_index REFERENCES indices (name) UNIQUE
);*/
data class MLRegRow(
    val id: Int,
    val name: String,
    val desc: String,
    val plugin: String,
    val db: String,
    val index: String
) {
    companion object {
        val columnNames = listOf("id", "name", "desc", "assign_plugin", "assign_db", "assign_index")
    }
}