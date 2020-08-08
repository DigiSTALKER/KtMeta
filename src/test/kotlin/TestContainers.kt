import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.github.hochikong.ktmeta.dbmgmt.DBConfigContainer
import io.github.hochikong.ktmeta.dbmgmt.DBRegJSONContainer
import io.github.hochikong.ktmeta.dbmgmt.ManagementInterface
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TestContainers {
    private val driverForSqlite = "org.sqlite.JDBC"
    private val dbPathForRead =
        "jdbc:sqlite:C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\testforread.db"

    private fun getResourceAsText(path: String): String {
        return object {}.javaClass.getResource(path).readText()
    }

    @Test
    fun testsetDBConfig() {
        val mgmt = ManagementInterface
        assertThrows<IllegalArgumentException> { mgmt.setDBConfig("", "") }
        assertThrows<IllegalArgumentException> { mgmt.setDBConfig("", dbPathForRead) }
        assertThrows<IllegalArgumentException> { mgmt.setDBConfig("org.sql.jdbc", dbPathForRead) }
        assertThrows<IllegalArgumentException> { mgmt.setDBConfig("org.sqlite.JDBC", "") }
        assertEquals(
            "DBConfigContainer(driver=$driverForSqlite, url=$dbPathForRead, username=null, password=null)",
            DBConfigContainer(driverForSqlite, dbPathForRead, null, null).toString()
        )
    }

    @Test
    fun testDBRegJson() {
        val pgjString: String = getResourceAsText("pg.json")
        println(pgjString)
        val mapper = ObjectMapper().registerKotlinModule()

        // deserialization
        val obj = mapper.readValue<DBRegJSONContainer>(pgjString)
        assertEquals("Postgresql", obj.db)
        assertEquals("NAME", obj.username)
        assertEquals("PASSWORD", obj.password)
        assertEquals(true, obj.encrypted)
        assertEquals("etc.", obj.dbs["etc."])
        assertEquals("URL 2", obj.dbs["DBNAME2"])

        val obj1 = DBRegJSONContainer(
            db = "Sqlite",
            username = null,
            password = null,
            encrypted = false,
            dbs = mapOf("A" to "URL A", "B" to "URL B"),
            descriptions = mapOf("A" to "DESC A")
        )
        val js = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj1)
        // File("test.json").writeText(js)
        println(js)
        assertEquals(getResourceAsText("sq.json"), js)
    }
}