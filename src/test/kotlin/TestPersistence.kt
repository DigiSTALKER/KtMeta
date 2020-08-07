import io.github.hochikong.ktmeta.dbmgmt.DBRegJSONContainer
import io.github.hochikong.ktmeta.dbmgmt.DBRegPersistence
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class TestPersistence {
    private val absp = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tmp"

    @Test
    fun testWrite() {
        val config = DBRegJSONContainer("Sqlite", null, null, false, mapOf("A" to "DESC A"))
        assertEquals(true, DBRegPersistence.saveTo(absPath = absp, data = config))
        val f = File("$absp\\dbregist.json")
        assert(f.isFile)
        assert(f.canRead())
    }

    @Test
    fun testLoad() {
        val obj = DBRegPersistence.loadFrom(absPath = absp)
        assertEquals("Sqlite", obj.db)
        assertEquals(null, obj.username)
        assertEquals(null, obj.password)
        assertEquals(false, obj.encrypted)
        assertEquals(mapOf("A" to "DESC A"), obj.dbs)
    }
}
