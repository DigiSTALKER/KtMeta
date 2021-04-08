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

object ESResourceRegister : ResourcesRegisterAPI {
    private val tableName = DAOTableNames.ESResource.tName
    private val dataSource = HikariDataSource(DAOConfigFactory.buildSqliteCPConfig(this.tableName))
    private val logger = LoggerFactory.getLogger(DAOConfigFactory.logKey[tableName])
    private val jdbiInstance: Jdbi = Jdbi.create(dataSource).apply {
        installPlugins()
    }

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
        @GetGeneratedKeys("id")
        @Transaction
        fun update(@Bind("id") id: Long, @BindBean("es") res: ESResourceRecord): Long


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

    override fun insertRecord(record: ResourcesRecord): Boolean {
        if (record is ESResourceRecord) {
            this.logger.info("Insert new record")

            val id = jdbiInstance.withExtension(ESDao::class.java, ExtensionCallback {
                it.insert(record)
            })

            return id != null
        }
        return false
    }

    override fun updateRecord(id: Long, newRecord: ResourcesRecord): Boolean {
        if (newRecord is ESResourceRecord) {
            this.logger.info("Update record")

            val idReturn = jdbiInstance.withExtension(ESDao::class.java, ExtensionCallback {
                it.update(id, newRecord)
            })

            return id == idReturn
        }
        return false
    }

    override fun getAllRecords(): List<ESResourceRecord> {
        this.logger.info("Get all records")

        return jdbiInstance.withExtension(ESDao::class.java, ExtensionCallback {
            it.query()
        })
    }

    override fun deleteRecord(id: Long): Boolean {
        this.logger.info("Delete a record")

        return id == jdbiInstance.withExtension(ESDao::class.java, ExtensionCallback {
            it.delete(id)
        })
    }

    override fun hasTable(): Boolean {
        this.logger.info("Checking has table or not")

        return try {
            val r = jdbiInstance.withExtension(ESDao::class.java, ExtensionCallback {
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
                val dao = it.attach(ESDao::class.java)
                this.logger.info("Drop table before create")
                dao.drop()
                this.logger.info("Create table")
                dao.createTable()
            }

            true
        } else {
            jdbiInstance.open().use {
                val dao = it.attach(ESDao::class.java)
                this.logger.info("Create table")
                dao.createTable()
            }
            true
        }
    }

    override fun drop() {
        if (this.hasTable()) {
            jdbiInstance.useExtension(ESDao::class.java, ExtensionConsumer {
                it.drop()
                this.logger.info("Drop table")
            })
        }
    }
}