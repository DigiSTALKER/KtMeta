import io.github.hochikong.ktmeta.dbmgmt.ManagementInterface
import me.liuwj.ktorm.database.Database
import me.liuwj.ktorm.support.sqlite.SQLiteDialect
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.sql.Connection

private fun getMgmt(): ManagementInterface {
    return ManagementInterface
}

class TestAPI {
    private val driverForSqlite = "org.sqlite.JDBC"
    private val dbPathForCreate =
        "jdbc:sqlite:C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\testforcreate.db"
    private val dbPathForRead =
        "jdbc:sqlite:C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\testforread.db"

    companion object {
        @JvmStatic
        @BeforeAll
        fun beforAll() = println("Test tasks start!!!")
    }

    @Test
    fun testInitialMgmt() {
        val mgmt = getMgmt()
        mgmt.setDBConfig(driverForSqlite, dbPathForRead)
        assert(mgmt.isDBUrlSet)
        assert(mgmt.isDriverSet)
    }

    @Test
    fun testgetJDBCConnection() {
        val mgmt = getMgmt()
        mgmt.setDBConfig(driverForSqlite, dbPathForRead)
        assert(mgmt.getJDBCConnection() is Connection)
    }

    @Test
    fun testgetDSLDatabase() {
        val mgmt = getMgmt()
        mgmt.setDBConfig(driverForSqlite, dbPathForRead)
        assert(mgmt.config.dialect is SQLiteDialect)
        assert(mgmt.getDSLDatabase() is Database)
    }
}