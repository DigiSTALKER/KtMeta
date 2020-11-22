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

package io.github.hochikong.ktmeta.service.shared_resources

import me.liuwj.ktorm.database.Database
import java.util.concurrent.ConcurrentHashMap
import javax.sql.DataSource

/**
 * Shared database references singleton.
 * Used by verticle to keep references of database connections.
 * */
object SharedDBRef {
    private val dbRefs = ConcurrentHashMap<String, Database>()
    private val dataSourceRefs = ConcurrentHashMap<String, DataSource>()

    fun addRef(key: String, db: Database) {
        dbRefs[key] = db
    }

    fun addRef(key: String, ds: DataSource) {
        dataSourceRefs[key] = ds
    }

    fun getDBRef(key: String): Database? {
        return dbRefs[key]
    }

    fun getDSRef(key: String): DataSource? {
        return dataSourceRefs[key]
    }

    fun removeDBRef(key: String) {
        dbRefs.remove(key)
    }

    fun removeDSRef(key: String) {
        dataSourceRefs.remove(key)
    }
}