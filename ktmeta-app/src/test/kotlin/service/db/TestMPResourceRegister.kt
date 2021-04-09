package service.db

import io.github.hochikong.ktmeta.dao.MPResourceRecord
import io.github.hochikong.ktmeta.dao.impl.MPResourceRegister
import org.junit.jupiter.api.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestMPResourceRegister {
    companion object {

        @JvmStatic
        @BeforeAll
        fun doBefore() {
            MPResourceRegister.drop()
        }

        @JvmStatic
        @AfterAll
        fun doAfter() {
            MPResourceRegister.drop()
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
        Assertions.assertEquals(false, MPResourceRegister.hasTable())

        // create it
        Assertions.assertEquals(true, MPResourceRegister.resetTable())

        // has table now
        Assertions.assertEquals(true, MPResourceRegister.hasTable())

        // insert
        Assertions.assertEquals(true, MPResourceRegister.insertRecord(t1))

        // query
        val rs = MPResourceRegister.getAllRecords()
        Assertions.assertEquals(rs[0].plugin_name, t1.plugin_name)
        Assertions.assertEquals(rs[0].plugin_class_name, t1.plugin_class_name)
        println("Query: ${rs[0]}")

        // update
        MPResourceRegister.updateRecord(rs[0].id, t2)
        val rs1 = MPResourceRegister.getAllRecords()
        Assertions.assertEquals(rs1[0].plugin_name, t2.plugin_name)
        println("Update ${rs1[0]}")

        // delete
        Assertions.assertEquals(true, MPResourceRegister.deleteRecord(rs1[0].id))
        println("After deleted: ${MPResourceRegister.getAllRecords()}")

    }
}