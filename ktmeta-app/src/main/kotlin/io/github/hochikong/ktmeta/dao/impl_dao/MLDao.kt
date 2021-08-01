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

import io.github.hochikong.ktmeta.dao.MLResourceRecord
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlScript
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.jdbi.v3.sqlobject.transaction.Transaction


interface MLDao {
    @SqlUpdate(
        """
            INSERT INTO metalibs_registration (lib_name, lib_desc, assign_plugin, assign_db, assign_index) 
            VALUES (:lib_name, :lib_desc, :assign_plugin, :assign_db, :assign_index); 
        """
    )
    @GetGeneratedKeys("id")
    @Transaction
    fun insert(@BindBean res: MLResourceRecord): Long


    @SqlUpdate(
        """
            UPDATE metalibs_registration
            SET lib_name = :ml.lib_name,
            lib_desc = :ml.lib_desc, 
            assign_plugin = :ml.assign_plugin, 
            assign_db = :ml.assign_db, 
            assign_index = :ml.assign_index
            WHERE id = :id;
        """
    )
    @GetGeneratedKeys("id")
    @Transaction
    fun update(@Bind("id") id: Long, @BindBean("ml") res: MLResourceRecord): Long


    @SqlQuery(
        """
            SELECT id, lib_name, lib_desc, assign_plugin, assign_db, assign_index
            FROM metalibs_registration;
        """
    )
    @RegisterBeanMapper(MLResourceRecord::class)
    fun query(): List<MLResourceRecord>


    @SqlUpdate("DELETE FROM metalibs_registration WHERE id = :id;")
    @GetGeneratedKeys("id")
    @Transaction
    fun delete(@Bind("id") id: Long): Long


    @SqlUpdate(
        """
            CREATE TABLE IF NOT EXISTS metalibs_registration
            (
            id       INTEGER PRIMARY KEY AUTOINCREMENT,
            lib_name TEXT                                                    NOT NULL UNIQUE,
            lib_desc TEXT                                                    NOT NULL,
            assign_plugin REFERENCES meta_plugins_registration (plugin_name) NOT NULL,
            assign_db REFERENCES dbs_registration (database) UNIQUE,
            assign_index REFERENCES indices_registration (index_name) UNIQUE
            );
        """
    )
    @Transaction
    fun createTable()


    @SqlQuery("SELECT DISTINCT 1 FROM metalibs_registration;")
    fun check(): Int?


    @SqlScript("DROP TABLE metalibs_registration;")
    @Transaction
    fun drop()
}