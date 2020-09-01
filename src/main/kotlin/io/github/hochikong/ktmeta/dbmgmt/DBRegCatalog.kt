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

package io.github.hochikong.ktmeta.dbmgmt

/**
 * Generate catalog by the return of Maintainer.queryAllRows
 * */
object DBRegCatalog {
    private var catalog: MutableMap<String, RegRow> = mutableMapOf()

    fun updateCatalog(queryResult: List<List<Any>>?): Boolean {
        // refresh
        if (catalog.isNotEmpty()) {
            catalog = mutableMapOf()
        }

        if (queryResult != null) {
            for (row in queryResult) {
                val tmp = row.regOut()
                catalog[tmp.name] = tmp
            }
            return true
        }
        return false
    }

    fun keys(): MutableSet<String> {
        return catalog.keys
    }

    operator fun get(key: String): RegRow? {
        if (key !in catalog.keys) return null
        return catalog[key]
    }

}