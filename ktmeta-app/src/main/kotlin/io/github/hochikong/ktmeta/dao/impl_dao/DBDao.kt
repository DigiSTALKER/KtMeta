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

import io.github.hochikong.ktmeta.dao.DBResourceRecord
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlScript
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.jdbi.v3.sqlobject.transaction.Transaction

interface DBDao {
    @SqlUpdate(
        """
            INSERT INTO dbs_registration(db_type, db_name, db_desc, db_url, user, password, save_passwd) 
            VALUES (:db_type, :db_name, :db_desc, :db_url, :user, :password, :save_passwd);
        """
    )
    @GetGeneratedKeys("id")
    @Transaction
    fun insert(@BindBean res: DBResourceRecord): Long


    @SqlUpdate(
        """
            UPDATE dbs_registration 
            SET db_type = :db.db_type, 
                db_name = :db.db_name, 
                db_desc = :db.db_desc, 
                db_url = :db.db_url, 
                user = :db.user, 
                password = :db.password, 
                save_passwd = :db.save_passwd
            WHERE id = :id;
        """
    )
    @GetGeneratedKeys("id")
    @Transaction
    fun update(@Bind("id") id: Long, @BindBean("db") res: DBResourceRecord): Long


    @SqlQuery(
        """
            SELECT id, db_type, db_name, db_desc, db_url, user, password, save_passwd FROM dbs_registration;
        """
    )
    @RegisterBeanMapper(DBResourceRecord::class)
    fun query(): List<DBResourceRecord>


    @SqlUpdate("DELETE FROM dbs_registration WHERE id = :id;")
    @GetGeneratedKeys("id")
    @Transaction
    fun delete(@Bind("id") id: Long): Long


    @SqlUpdate(
        """
            CREATE TABLE IF NOT EXISTS dbs_registration
            (
            id          INTEGER PRIMARY KEY AUTOINCREMENT,
            db_type     TEXT    NOT NULL,        -- database type
            db_name     TEXT    NOT NULL UNIQUE, -- database name
            db_desc     TEXT    NOT NULL,        -- database description
            db_url      TEXT    NOT NULL UNIQUE, -- database url
            user        TEXT,                    -- database username
            password    TEXT,                    -- database password (encrypted)
            save_passwd INTEGER NOT NULL,        -- has password or not, 0 -> false; 1 -> true
            CONSTRAINT db_type_check CHECK ( db_type IN ('Sqlite', 'Postgresql', 'Mysql', 'H2') ),
            CONSTRAINT save_passwd_or_not CHECK ( save_passwd IN (0, 1))
            );
        """
    )
    @Transaction
    fun createTable()


    @SqlQuery("SELECT DISTINCT 1 FROM dbs_registration;")
    fun check(): Int?


    @SqlScript("DROP TABLE dbs_registration;")
    @Transaction
    fun drop()
}