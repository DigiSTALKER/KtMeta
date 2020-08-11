import io.github.hochikong.ktmeta.dbmgmt.Maintainer
import io.github.hochikong.ktmeta.dbmgmt.regIn
import io.github.hochikong.ktmeta.dbmgmt.regOut
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class TestRegConverter {
    private val rawInput = listOf("Sqlite", null, null, "desc sqlite", "url 1", false)
    private val input = listOf("'Sqlite'", "null", "null", "'desc sqlite'", "'url 1'", "0")
    private val rawOutput = listOf(1, "Sqlite", "null", "null", "desc sqlite", "url 1", 0)
    private val output = listOf(1, "Sqlite", null, null, "desc sqlite", "url 1", false)

    companion object {
        @AfterAll
        @JvmStatic
        fun afterAll() {
            Maintainer.dropTable()
        }

        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            if (!Maintainer.hasTable()) {
                Maintainer.createTable()
            } else {
                Maintainer.dropTable()
                Maintainer.createTable()
            }
        }
    }

    @Test
    fun testRegInOut() {
        assert(rawInput.regIn() == input)
        assert(rawOutput.regOut() == output)
    }

    @Test
    fun testRegInOutWithDB() {
        rawInput.regIn()?.let { Maintainer.insertData(it) }
        assert(Maintainer.queryAllData()?.get(0)?.regOut() == output)
    }
}