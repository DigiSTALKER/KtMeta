import io.github.hochikong.ktmeta.dbmgmt.DBRegCatalog
import io.github.hochikong.ktmeta.dbmgmt.Maintainer
import io.github.hochikong.ktmeta.dbmgmt.RegRow
import io.github.hochikong.ktmeta.predefined.SupportedDBs
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class TestContainers {
    private val data = RegRow(
        -1,
        SupportedDBs.PostgreSQL,
        "postgres",
        "postgres",
        "testdb",
        "this is a test db",
        "jdbc:postgresql",
        true
    )
    private val data1 = RegRow(
        -1,
        SupportedDBs.SQLite,
        "null",
        "null",
        "sqlite",
        "this is a test db for sqlite",
        "jdbc:sqlite",
        false
    )

    companion object {
        @JvmStatic
        @BeforeAll
        fun bA() {
            if (!Maintainer.hasTable()) {
                Maintainer.createTable()
            } else {
                Maintainer.dropTable()
                Maintainer.createTable()
            }
        }

        @JvmStatic
        @AfterAll
        fun aA() {
            Maintainer.dropTable()
        }
    }

    @Test
    fun testDBRegCatalog() {
        Maintainer.insertRow(data.regIn())
        Maintainer.insertRow(data1.regIn())
        DBRegCatalog.updateCatalog(Maintainer.queryAllRows())
        assertEquals(2, DBRegCatalog.keys().size)

        val result = DBRegCatalog["testdb"]
        if (result != null) {
            assertEquals(1, result.id)
            assertEquals("postgres", result.user)
            assertEquals("postgres", result.password)
            assertEquals(SupportedDBs.PostgreSQL, result.db)
            assertEquals("this is a test db", result.description)
            assertEquals("jdbc:postgresql", result.url)
            assertEquals(true, result.protected)
        }

        val result1 = DBRegCatalog["sqlite"]
        if (result1 != null) {
            assertEquals(2, result1.id)
            assertEquals("null", result1.user)
            assertEquals("null", result1.password)
            assertEquals(SupportedDBs.SQLite, result1.db)
            assertEquals("this is a test db for sqlite", result1.description)
            assertEquals("jdbc:sqlite", result1.url)
            assertEquals(false, result1.protected)

            assertEquals(false, DBRegCatalog.updateCatalog(null))
            assertEquals(null, DBRegCatalog["nmsl"])
        }
    }
}