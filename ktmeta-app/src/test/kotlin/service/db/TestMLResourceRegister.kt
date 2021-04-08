package service.db

import io.github.hochikong.ktmeta.dao.MLResourceRecord
import io.github.hochikong.ktmeta.dao.MPResourceRecord
import io.github.hochikong.ktmeta.dao.impl.MLResourceRegister
import io.github.hochikong.ktmeta.dao.impl.MPResourceRegister
import org.junit.jupiter.api.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestMLResourceRegister {
    companion object {

        @JvmStatic
        @BeforeAll
        fun doBefore() {
            MLResourceRegister.drop()
            MPResourceRegister.drop()
        }

        @JvmStatic
        @AfterAll
        fun doAfter() {
            MLResourceRegister.drop()
            MPResourceRegister.drop()
        }
    }

    val t1 = MLResourceRecord(
        lib_name = "ml1",
        lib_desc = "desc 1",
        assign_plugin = "p1",
        assign_db = "",
        assign_index = ""
    )

    val t2 = MLResourceRecord(
        lib_name = "ml1 update",
        lib_desc = "desc 1 update",
        assign_plugin = "p1",
        assign_db = "",
        assign_index = ""
    )

    @Test
    @Order(1)
    fun all() {
        MPResourceRegister.resetTable()
        MPResourceRegister.insertRecord(
            MPResourceRecord(
                plugin_name = "p1",
                plugin_version = "v0.0.1",
                plugin_class_name = "xxx.xxxx.class",
                plugin_desc = "desc plug",
                plugin_helper = "plug help"
            )
        )

        // no table first
        Assertions.assertEquals(false, MLResourceRegister.hasTable())

        // create it
        Assertions.assertEquals(true, MLResourceRegister.resetTable())

        // has table now
        Assertions.assertEquals(true, MLResourceRegister.hasTable())

        // insert
        Assertions.assertEquals(true, MLResourceRegister.insertRecord(t1))

        // query
        val rs = MLResourceRegister.getAllRecords()
        Assertions.assertEquals(rs[0].lib_name, t1.lib_name)
        Assertions.assertEquals(rs[0].assign_plugin, t1.assign_plugin)
        println("Query: ${rs[0]}")

        // update
        MLResourceRegister.updateRecord(rs[0].id, t2)
        val rs1 = MLResourceRegister.getAllRecords()
        Assertions.assertEquals(rs1[0].lib_name, t2.lib_name)
        println("Update ${rs1[0]}")

        // delete
        Assertions.assertEquals(true, MLResourceRegister.deleteRecord(rs1[0].id))
        println("After deleted: ${MLResourceRegister.getAllRecords()}")

    }
}