package dao.jdbitest

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.extension.ExtensionConsumer
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import javax.sql.DataSource

object JDBIOBJ {
    @JvmStatic
    fun main(args: Array<String>) {
        val cfg = JDBICFG()
        cfg.jdbi.useExtension(Drinks::class.java, ExtensionConsumer<Drinks, RuntimeException> { dao: Drinks ->
            dao.createTable()
            dao.insert("coffie")
            dao.insert("tea")
        })
    }
}

internal class JDBICFG {
    var url = "jdbc:postgresql://192.168.142.130:5432/testdb"
    var cfg = HikariConfig()
    var ds: DataSource
    var jdbi: Jdbi

    init {
        cfg.jdbcUrl = url
        cfg.username = "dbuser"
        cfg.password = "dbuserpass"
        ds = HikariDataSource(cfg)
        jdbi = Jdbi.create(ds)
        jdbi.installPlugin(SqlObjectPlugin())
    }
}

internal interface Drinks {
    @SqlUpdate("CREATE TABLE drinks (id BIGSERIAL PRIMARY KEY , name VARCHAR(200))")
    fun createTable()

    @SqlUpdate("INSERT INTO drinks(name) VALUES (:dn)")
    fun insert(@Bind("dn") drinkName: String?)
}