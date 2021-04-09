package io.github.hochikong.ktmeta.dao.impl

import com.zaxxer.hikari.HikariDataSource
import io.github.hochikong.ktmeta.dao.*
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.extension.ExtensionCallback
import org.jdbi.v3.core.extension.ExtensionConsumer
import org.jdbi.v3.core.statement.UnableToCreateStatementException
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlScript
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.jdbi.v3.sqlobject.transaction.Transaction
import org.slf4j.LoggerFactory

object MLResourceRegister : ResourcesRegisterAPI {
    private val tableName = DAOTableNames.MLResource.tName
    private val dataSource = HikariDataSource(DAOConfigFactory.buildSqliteCPConfig(this.tableName))
    private val logger = LoggerFactory.getLogger(DAOConfigFactory.logKey[tableName])
    private val jdbiInstance: Jdbi = Jdbi.create(dataSource).apply {
        installPlugins()
    }

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