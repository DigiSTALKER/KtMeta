/*
 * Copyright 2021 Hochikong
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

package io.github.hochikong.ktmeta.dao.impl_dao

import io.github.hochikong.ktmeta.dao.MPResourceRecord
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlScript
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.jdbi.v3.sqlobject.transaction.Transaction

interface MPDao {
    @SqlUpdate(
        """
            INSERT INTO metaplugins_registration (plugin_name, plugin_version, plugin_class_name, plugin_desc, plugin_helper)
            VALUES (:plugin_name, :plugin_version, :plugin_class_name, :plugin_desc, :plugin_helper);
        """
    )
    @GetGeneratedKeys("id")
    @Transaction
    fun insert(@BindBean res: MPResourceRecord): Long


    @SqlUpdate(
        """
            UPDATE metaplugins_registration
            SET plugin_name       = :mp.plugin_name,
            plugin_version    = :mp.plugin_version,
            plugin_class_name = :mp.plugin_class_name,
            plugin_desc       = :mp.plugin_desc,
            plugin_helper     = :mp.plugin_helper
            WHERE id = :id;
        """
    )
    @GetGeneratedKeys("id")
    @Transaction
    fun update(@Bind("id") id: Long, @BindBean("mp") res: MPResourceRecord): Long


    @SqlQuery(
        """
            SELECT id, plugin_name, plugin_version, plugin_class_name, plugin_desc, plugin_helper
            FROM metaplugins_registration;
        """
    )
    @RegisterBeanMapper(MPResourceRecord::class)
    fun query(): List<MPResourceRecord>


    @SqlUpdate("DELETE FROM metaplugins_registration WHERE id = :id;")
    @GetGeneratedKeys("id")
    @Transaction
    fun delete(@Bind("id") id: Long): Long


    @SqlUpdate(
        """
            CREATE TABLE IF NOT EXISTS metaplugins_registration
            (
            id                INTEGER PRIMARY KEY AUTOINCREMENT,
            plugin_name       TEXT NOT NULL UNIQUE,
            plugin_version    TEXT NOT NULL UNIQUE,
            plugin_class_name TEXT NOT NULL, -- plugin class name, use reflection to load plugins
            plugin_desc       TEXT NOT NULL,
            plugin_helper     TEXT NOT NULL  -- plugin help message
            );
        """
    )
    @Transaction
    fun createTable()


    @SqlQuery("SELECT DISTINCT 1 FROM metaplugins_registration;")
    fun check(): Int?


    @SqlScript("DROP TABLE metaplugins_registration;")
    @Transaction
    fun drop()
}