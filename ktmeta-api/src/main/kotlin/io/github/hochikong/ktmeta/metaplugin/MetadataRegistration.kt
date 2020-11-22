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

interface MetadataRegistration {
    // Use db to fetch connection then create all tables for storing metadata.
    /**
     * Create all tables for plugin to store metadata.
     * @param db Database name, DBMgmt.checkCatalog() should contains it.
     * @param libraryName Metadata library' name. Used for creating tables.
     * */
    fun createTables(db: String, libraryName: String): Boolean

    /**
     * Create tag table for [libraryName]
     * @param db Database name, DBMgmt.checkCatalog() should contains it.
     * @param libraryName Metadata library' name. Used for creating tables.
     * */
    fun createTagTable(db: String, libraryName: String): Boolean
}