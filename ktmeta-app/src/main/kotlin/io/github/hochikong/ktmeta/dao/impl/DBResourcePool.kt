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
import io.github.hochikong.ktmeta.dao.impl_dao.DBDao
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.extension.ExtensionCallback
import org.jdbi.v3.core.extension.ExtensionConsumer
import org.jdbi.v3.core.statement.UnableToCreateStatementException
import org.slf4j.LoggerFactory


object DBResourcePool : ResourcesRegisterAPI {
    private val tableName = DAOTableNames.DBResource.tName
    private val dataSource = HikariDataSource(DAOConfigFactory.buildSqliteCPConfig(this.tableName))
    private val logger = LoggerFactory.getLogger(DAOConfigFactory.logKey[tableName])
    private val jdbiInstance: Jdbi = Jdbi.create(dataSource).apply {
        installPlugins()
    }


    /**
     * @throws org.jdbi.v3.core.statement.UnableToCreateStatementException
     * */
    override fun insertRecord(record: ResourcesRecord): Boolean {
        if (record is DBResourceRecord) {
            this.logger.info("Insert new record")

            val id = jdbiInstance.withExtension(DBDao::class.java, ExtensionCallback {
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

            val idReturn = jdbiInstance.withExtension(DBDao::class.java, ExtensionCallback {
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

        return jdbiInstance.withExtension(DBDao::class.java, ExtensionCallback {
            it.query()
        })
    }

    /**
     * @throws org.jdbi.v3.core.statement.UnableToCreateStatementException
     * */
    override fun deleteRecord(id: Long): Boolean {
        this.logger.info("Delete a record")

        return id == jdbiInstance.withExtension(DBDao::class.java, ExtensionCallback {
            it.delete(id)
        })
    }


    override fun hasTable(): Boolean {
        this.logger.info("Checking has table or not")

        return try {
            val r = jdbiInstance.withExtension(DBDao::class.java, ExtensionCallback {
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
                val dao = it.attach(DBDao::class.java)
                this.logger.info("Drop table before create")
                dao.drop()
                this.logger.info("Create table")
                dao.createTable()
            }

            true
        } else {
            jdbiInstance.open().use {
                val dao = it.attach(DBDao::class.java)
                this.logger.info("Create table")
                dao.createTable()
            }
            true
        }
    }

    override fun drop() {
        if (this.hasTable()) {
            jdbiInstance.useExtension(DBDao::class.java, ExtensionConsumer {
                it.drop()
                this.logger.info("Drop table")
            })
        }
    }

    /**
     * Use a name to get a database resource record, only one result should be returned.
     *
     * If nothing found, return a default/empty DBResourceRecord and its id is -1.
     * */
    fun getRecordByName(db: String): DBResourceRecord {
        try {
            this.jdbiInstance.open().use {
                val opt = it.createQuery(
                    """
                    SELECT id, db_type, db_name, db_desc, db_url, user, password, save_passwd 
                    FROM dbs_registration
                    WHERE db_name = :name;
                """.trimIndent()
                )
                    .bind("name", db)
                    .mapToBean(DBResourceRecord::class.java)
                    .findOne()

                if (opt.isPresent) {
                    return opt.get()
                }
            }
        } catch (e: IllegalStateException) {
            // do nothing
        }

        // return empty one
        return DBResourceRecord()
    }
}