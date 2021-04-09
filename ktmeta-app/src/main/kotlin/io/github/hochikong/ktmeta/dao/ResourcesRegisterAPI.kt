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

interface ResourcesRegisterAPI {
    /**
     * Insert a new record into database
     *
     * @param record, ResourcesRecord or its subclasses
     * */
    fun insertRecord(record: ResourcesRecord): Boolean

    /**
     * Update a record in the database by a new record that has a different value in one or all fields,
     * according to an existing id.
     *
     * @param newRecord, ResourcesRecord or its subclasses
     * */
    fun updateRecord(id: Long, newRecord: ResourcesRecord): Boolean

    /**
     * Return all records in database
     * */
    fun getAllRecords(): List<ResourcesRecord>

    /**
     * Delete a record according to an existing id.
     * */
    fun deleteRecord(id: Long): Boolean

    /**
     * Check the table exists or not. This method should catch all exceptions.
     * */
    fun hasTable(): Boolean

    /**
     * Drop the existing table then recreate it. Or just truncate it. This method relies on hasTable()
     * */
    fun resetTable(): Boolean

    /**
     * Drop the table. This method relies on hasTable()
     * */
    fun drop()
}