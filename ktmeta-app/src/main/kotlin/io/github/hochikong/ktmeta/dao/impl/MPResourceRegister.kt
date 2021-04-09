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

object MPResourceRegister : ResourcesRegisterAPI {
    private val tableName = DAOTableNames.MPResource.tName
    private val dataSource = HikariDataSource(DAOConfigFactory.buildSqliteCPConfig(this.tableName))
    private val logger = LoggerFactory.getLogger(DAOConfigFactory.logKey[tableName])
    private val jdbiInstance: Jdbi = Jdbi.create(dataSource).apply {
        installPlugins()
    }

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

    override fun insertRecord(record: ResourcesRecord): Boolean {
        if (record is MPResourceRecord) {
            this.logger.info("Insert new record")

            val id = jdbiInstance.withExtension(MPDao::class.java, ExtensionCallback {
                it.insert(record)
            })

            return id != null
        }
        return false
    }

    override fun updateRecord(id: Long, newRecord: ResourcesRecord): Boolean {
        if (newRecord is MPResourceRecord) {
            this.logger.info("Update record")

            val idReturn = jdbiInstance.withExtension(MPDao::class.java, ExtensionCallback {
                it.update(id, newRecord)
            })

            return id == idReturn
        }
        return false
    }

    override fun getAllRecords(): List<MPResourceRecord> {
        this.logger.info("Get all records")

        return jdbiInstance.withExtension(MPDao::class.java, ExtensionCallback {
            it.query()
        })
    }

    override fun deleteRecord(id: Long): Boolean {
        this.logger.info("Delete a record")

        return id == jdbiInstance.withExtension(MPDao::class.java, ExtensionCallback {
            it.delete(id)
        })
    }

    override fun hasTable(): Boolean {
        this.logger.info("Checking has table or not")

        return try {
            val r = jdbiInstance.withExtension(MPDao::class.java, ExtensionCallback {
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
                val dao = it.attach(MPDao::class.java)
                this.logger.info("Drop table before create")
                dao.drop()
                this.logger.info("Create table")
                dao.createTable()
            }

            true
        } else {
            jdbiInstance.open().use {
                val dao = it.attach(MPDao::class.java)
                this.logger.info("Create table")
                dao.createTable()
            }
            true
        }
    }

    override fun drop() {
        if (this.hasTable()) {
            jdbiInstance.useExtension(MPDao::class.java, ExtensionConsumer {
                it.drop()
                this.logger.info("Drop table")
            })
        }
    }
}