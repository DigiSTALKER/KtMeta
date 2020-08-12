import io.github.hochikong.ktmeta.dbmgmt.Maintainer
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestMaintainer {
    /**
     * id INTEGER PRIMARY KEY AUTOINCREMENT ,
     * db TEXT NOT NULL ,
     * user TEXT,
     * password TEXT,
     * description TEXT NOT NULL ,
     * url TEXT NOT NULL ,
     * protected INTEGER NOT NULL ,
     * */
    private val legalData = listOf(
        listOf("'Sqlite'", "null", "null", "'desc sqlite'", "'url 1'", '0'),
        listOf("'Postgresql'", "'postgres'", "'postgres'", "'desc postgresql'", "'url 2'", '1')
    )

    private val illegalData = listOf("'Mysql'", "'mysql'", "'mysql'", "'desc mysql'", "'url 3'", '1')

    companion object {
        @JvmStatic
        @AfterAll
        fun afterAll() {
            Maintainer.dropTable()
        }

        @JvmStatic
        @BeforeAll
        fun beforAll() {
            if (Maintainer.hasTable()) {
                Maintainer.dropTable()
            }
        }
    }

    @Order(1)
    @Test
    fun testMaintainer() {
        println("tm")
        assertEquals(false, Maintainer.hasTable())
        Maintainer.createTable()
    }

    @Order(2)
    @Test
    fun testInsertTable() {
        assertEquals(false, Maintainer.createTable())
        assertEquals(true, Maintainer.insertRow(legalData[0]))
        assertEquals(true, Maintainer.insertRow(legalData[1]))
    }

    @Order(3)
    @Test
    fun testQueryTable() {
        Maintainer.createTable()
        assertEquals(false, Maintainer.insertRow(legalData[0]))
        val result = Maintainer.queryAllRow()
        println(result)
        assertEquals(listOf(1, "Sqlite", "null", "null", "desc sqlite", "url 1", 0), result?.get(0))
    }

    @Order(4)
    @Test
    fun testIllegal() {
        assertEquals(false, Maintainer.insertRow(illegalData))
    }

    @Order(5)
    @Test
    fun testUpdateAndDelete() {
        assertThrows<IllegalArgumentException> {
            Maintainer.updateRow(
                "sb",
                "222",
                "protected == 1"
            )
        }
        Maintainer.updateRow(
            "description",
            "'new desc sqlite'",
            "db == 'Sqlite' AND protected == 0"
        )
        assertEquals(
            listOf(1, "Sqlite", "null", "null", "new desc sqlite", "url 1", 0).toString(),
            Maintainer.queryAllRow()?.get(0).toString()
        )
        Maintainer.deleteRow("db == 'Sqlite'")
        assertEquals(
            listOf(2, "Postgresql", "postgres", "postgres", "desc postgresql", "url 2", '1').toString(),
            Maintainer.queryAllRow()?.get(0).toString()
        )
    }
}