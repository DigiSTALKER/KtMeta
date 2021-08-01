package service.db

import io.github.hochikong.ktmeta.dao.MPResourceRecord
import io.github.hochikong.ktmeta.dao.impl.MPResourcePool
import org.junit.jupiter.api.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestMPResourcePool {
    companion object {

        @JvmStatic
        @BeforeAll
        fun doBefore() {
            MPResourcePool.drop()
        }

        @JvmStatic
        @AfterAll
        fun doAfter() {
            MPResourcePool.drop()
        }
    }

    val t1 = MPResourceRecord(
        plugin_name = "plug 1",
        plugin_version = "v0.0.1",
        plugin_class_name = "xxx.xxxx.class",
        plugin_desc = "desc plug",
        plugin_helper = "plug help"
    )

    val t2 = MPResourceRecord(
        plugin_name = "plug 2",
        plugin_version = "v0.0.2",
        plugin_class_name = "xxx.xxxx.class",
        plugin_desc = "desc plug",
        plugin_helper = "plug help"
    )

    @Test
    @Order(1)
    fun all() {
        // no table first
        Assertions.assertEquals(false, MPResourcePool.hasTable())

        // create it
        Assertions.assertEquals(true, MPResourcePool.resetTable())

        // has table now
        Assertions.assertEquals(true, MPResourcePool.hasTable())

        // insert
        Assertions.assertEquals(true, MPResourcePool.insertRecord(t1))

        // query
        val rs = MPResourcePool.getAllRecords()
        Assertions.assertEquals(rs[0].plugin_name, t1.plugin_name)
        Assertions.assertEquals(rs[0].plugin_class_name, t1.plugin_class_name)
        println("Query: ${rs[0]}")

        // update
        MPResourcePool.updateRecord(rs[0].id, t2)
        val rs1 = MPResourcePool.getAllRecords()
        Assertions.assertEquals(rs1[0].plugin_name, t2.plugin_name)
        println("Update ${rs1[0]}")

        // delete
        Assertions.assertEquals(true, MPResourcePool.deleteRecord(rs1[0].id))
        println("After deleted: ${MPResourcePool.getAllRecords()}")

    }
}