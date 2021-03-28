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

package io.github.hochikong.ktmeta.service.metaplugin

/**
 * All metadata plugins can implement this interface to add ElasticSearch support.
 * This interface supports the use of RDBMS or ElasticSearch to store metadata.
 *
 * Additional supported features:
 * 1. Copy data to ElasticSearch
 * 2. Copy changed data to ElasticSearch
 * 3. Full text search on ElasticSearch
 * 4. Attribute Search on ElasticSearch
 * 5. Advance Search on ElasticSearch
 * */
interface AdvMetaPluginAPI : BasicMetaPluginAPI {
    /**
     * Clean the ElasticSearch index then copy all data from DBMS to ElasticSearch.
     * The database and Index should in the same Metadata Library.
     * */
    fun fullSyncToES(): Boolean

    /**
     * Copy all changes in DBMS to ES and update Index
     * */
    fun syncToES(): Boolean


    /**
     * Use [rawInput] execute full text search.
     * @param rawInput String, from full text search's GUI form.
     * @param targetLib Int, target metadata library's id.
     * @param newTab Boolean, create a new tab to show results.
     * */
    fun fullTextSearchOnES(rawInput: String, targetLib: Int, newTab: Boolean): List<Any>

    /**
     * Use rules to execute advance search.
     * */
    fun advanceSearchOnES(rules: List<SearchRules>, targetLib: Int, newTab: Boolean): List<Any>

    /**
     * Find out all metadata which its [attribute] exactly equals to [value].
     * */
    fun attrSearchOnES(attribute: String, value: String, targetLib: Int, newTab: Boolean): List<Any>
}