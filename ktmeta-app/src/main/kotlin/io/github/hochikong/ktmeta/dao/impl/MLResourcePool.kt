package io.github.hochikong.ktmeta.dao.impl

import com.zaxxer.hikari.HikariDataSource
import io.github.hochikong.ktmeta.dao.*
import io.github.hochikong.ktmeta.dao.impl_dao.MLDao
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.extension.ExtensionCallback
import org.jdbi.v3.core.extension.ExtensionConsumer
import org.jdbi.v3.core.statement.UnableToCreateStatementException
import org.slf4j.LoggerFactory

object MLResourcePool : ResourcesRegisterAPI {
    private val tableName = DAOTableNames.MLResource.tName
    private val dataSource = HikariDataSource(DAOConfigFactory.buildSqliteCPConfig(this.tableName))
    private val logger = LoggerFactory.getLogger(DAOConfigFactory.logKey[tableName])
    private val jdbiInstance: Jdbi = Jdbi.create(dataSource).apply {
        installPlugins()
    }


    override fun insertRecord(record: ResourcesRecord): Boolean {
        if (record is MLResourceRecord) {
            this.logger.info("Insert new record")

            val id = jdbiInstance.withExtension(MLDao::class.java, ExtensionCallback {
                it.insert(record)
            })

            return id != null
        }
        return false
    }

    override fun updateRecord(id: Long, newRecord: ResourcesRecord): Boolean {
        if (newRecord is MLResourceRecord) {
            this.logger.info("Update record")

            val idReturn = jdbiInstance.withExtension(MLDao::class.java, ExtensionCallback {
                it.update(id, newRecord)
            })

            return id == idReturn
        }
        return false
    }

    override fun getAllRecords(): List<MLResourceRecord> {
        this.logger.info("Get all records")

        return jdbiInstance.withExtension(MLDao::class.java, ExtensionCallback {
            it.query()
        })
    }

    override fun deleteRecord(id: Long): Boolean {
        this.logger.info("Delete a record")

        return id == jdbiInstance.withExtension(MLDao::class.java, ExtensionCallback {
            it.delete(id)
        })
    }

    override fun hasTable(): Boolean {
        this.logger.info("Checking has table or not")

        return try {
            val r = jdbiInstance.withExtension(MLDao::class.java, ExtensionCallback {
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
                val dao = it.attach(MLDao::class.java)
                this.logger.info("Drop table before create")
                dao.drop()
                this.logger.info("Create table")
                dao.createTable()
            }

            true
        } else {
            jdbiInstance.open().use {
                val dao = it.attach(MLDao::class.java)
                this.logger.info("Create table")
                dao.createTable()
            }
            true
        }
    }

    override fun drop() {
        if (this.hasTable()) {
            jdbiInstance.useExtension(MLDao::class.java, ExtensionConsumer {
                it.drop()
                this.logger.info("Drop table")
            })
        }
    }
}