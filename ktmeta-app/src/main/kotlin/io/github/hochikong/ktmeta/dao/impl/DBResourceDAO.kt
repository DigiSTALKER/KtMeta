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

package io.github.hochikong.ktmeta.dao.impl

import com.zaxxer.hikari.HikariDataSource
import io.github.hochikong.ktmeta.dao.*
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.extension.ExtensionCallback
import org.jdbi.v3.core.extension.ExtensionConsumer
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.jdbi.v3.sqlobject.transaction.Transaction
import org.slf4j.LoggerFactory
import org.jdbi.v3.core.statement.UnableToCreateStatementException
import org.jdbi.v3.sqlobject.statement.SqlScript


object DBResourceDAO : ResourcesDAOAPI {
    private val tableName = DAOTableNames.DBResource.tName
    private val dataSource = HikariDataSource(DAOConfigFactory.buildSqliteCPConfig(this.tableName))
    private val logger = LoggerFactory.getLogger(DAOConfigFactory.logKey[tableName])
    private val jdbiInstance: Jdbi = Jdbi.create(dataSource).apply {
        installPlugins()
    }

    interface DBR {
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

    /**
     * @throws org.jdbi.v3.core.statement.UnableToCreateStatementException
     * */
    override fun insertRecord(record: ResourcesRecord): Boolean {
        if (record is DBResourceRecord) {
            this.logger.info("Insert new record")

            val id = jdbiInstance.withExtension(DBR::class.java, ExtensionCallback {
                it.insert(record)
            })

            return id != null
        }
        return false
    }

    /**
     * @throws org.jdbi.v3.core.statement.UnableToCreateStatementException
     * */
    override fun updateRecord(id: Long, newRecord: ResourcesRecord): Boolean {
        if (newRecord is DBResourceRecord) {
            this.logger.info("Update record")

            val idReturn = jdbiInstance.withExtension(DBR::class.java, ExtensionCallback {
                it.update(id, newRecord)
            })

            return id == idReturn
        }
        return false
    }

    /**
     * @throws org.jdbi.v3.core.statement.UnableToCreateStatementException
     * */
    override fun getAllRecords(): List<DBResourceRecord> {
        this.logger.info("Get all records")

        return jdbiInstance.withExtension(DBR::class.java, ExtensionCallback {
            it.query()
        })
    }

    /**
     * @throws org.jdbi.v3.core.statement.UnableToCreateStatementException
     * */
    override fun deleteRecord(id: Long): Boolean {
        this.logger.info("Delete a record")

        return id == jdbiInstance.withExtension(DBR::class.java, ExtensionCallback {
            it.delete(id)
        })
    }


    override fun hasTable(): Boolean {
        this.logger.info("Checking has table or not")

        return try {
            val r = jdbiInstance.withExtension(DBR::class.java, ExtensionCallback {
                it.check()
            })

            // if has table but no data
            if (r == null) {
                true
            } else {
                // has table has a least one row
                1 == r
            }
        } catch (e: UnableToCreateStatementException) {
            this.logger.info("Table not found")
            false
        }
    }

    override fun resetTable(): Boolean {
        return if (this.hasTable()) {
            jdbiInstance.open().use {
                val dao = it.attach(DBR::class.java)
                this.logger.info("Drop table before create")
                dao.drop()
                this.logger.info("Create table")
                dao.createTable()
            }

            true
        } else {
            jdbiInstance.open().use {
                val dao = it.attach(DBR::class.java)
                this.logger.info("Create table")
                dao.createTable()
            }
            true
        }
    }

    override fun drop() {
        if (this.hasTable()) {
            jdbiInstance.useExtension(DBR::class.java, ExtensionConsumer {
                it.drop()
                this.logger.info("Drop table")
            })
        }
    }
}