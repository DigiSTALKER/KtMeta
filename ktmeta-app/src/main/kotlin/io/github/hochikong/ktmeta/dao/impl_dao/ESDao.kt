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

import io.github.hochikong.ktmeta.dao.ESResourceRecord
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlScript
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.jdbi.v3.sqlobject.transaction.Transaction

interface ESDao {
    @SqlUpdate(
        """
            INSERT INTO indices_registration(index_name, index_desc, index_url)
            VALUES (:index_name, :index_desc, :index_url);
        """
    )
    @GetGeneratedKeys("id")
    @Transaction
    fun insert(@BindBean res: ESResourceRecord): Long


    @SqlUpdate(
        """
            UPDATE indices_registration
            SET index_name = :es.index_name,
            index_desc = :es.index_desc,
            index_url = :es.index_url
            WHERE id = :id;
        """
    )
    @Transaction
    fun update(@Bind("id") id: Long, @BindBean("es") res: ESResourceRecord): Int


    @SqlQuery(
        """
            SELECT id, index_name, index_desc, index_url FROM indices_registration;
        """
    )
    @RegisterBeanMapper(ESResourceRecord::class)
    fun query(): List<ESResourceRecord>


    @SqlUpdate("DELETE FROM indices_registration WHERE id = :id;")
    @GetGeneratedKeys("id")
    @Transaction
    fun delete(@Bind("id") id: Long): Long


    @SqlUpdate(
        """
            CREATE TABLE IF NOT EXISTS indices_registration
            (
            id         INTEGER PRIMARY KEY AUTOINCREMENT,
            index_name TEXT NOT NULL UNIQUE, -- elasticsearch index name
            index_desc TEXT NOT NULL, -- elasticsearch index description
            index_url  TEXT NOT NULL UNIQUE -- elasticsearch index url
            );
        """
    )
    @Transaction
    fun createTable()


    @SqlQuery("SELECT DISTINCT 1 FROM indices_registration;")
    fun check(): Int?


    @SqlScript("DROP TABLE indices_registration;")
    @Transaction
    fun drop()
}