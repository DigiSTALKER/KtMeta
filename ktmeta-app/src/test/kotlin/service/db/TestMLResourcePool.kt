package service.db

import io.github.hochikong.ktmeta.dao.MLResourceRecord
import io.github.hochikong.ktmeta.dao.MPResourceRecord
import io.github.hochikong.ktmeta.dao.impl.MLResourcePool
import io.github.hochikong.ktmeta.dao.impl.MPResourcePool
import org.junit.jupiter.api.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestMLResourcePool {
    companion object {

        @JvmStatic
        @BeforeAll
        fun doBefore() {
            MLResourcePool.drop()
            MPResourcePool.drop()
        }

        @JvmStatic
        @AfterAll
        fun doAfter() {
            MLResourcePool.drop()
            MPResourcePool.drop()
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
        MPResourcePool.resetTable()
        MPResourcePool.insertRecord(
            MPResourceRecord(
                plugin_name = "p1",
                plugin_version = "v0.0.1",
                plugin_class_name = "xxx.xxxx.class",
                plugin_desc = "desc plug",
                plugin_helper = "plug help"
            )
        )

        // no table first
        Assertions.assertEquals(false, MLResourcePool.hasTable())

        // create it
        Assertions.assertEquals(true, MLResourcePool.resetTable())

        // has table now
        Assertions.assertEquals(true, MLResourcePool.hasTable())

        // insert
        Assertions.assertEquals(true, MLResourcePool.insertRecord(t1))

        // query
        val rs = MLResourcePool.getAllRecords()
        Assertions.assertEquals(rs[0].lib_name, t1.lib_name)
        Assertions.assertEquals(rs[0].assign_plugin, t1.assign_plugin)
        println("Query: ${rs[0]}")

        // update
        MLResourcePool.updateRecord(rs[0].id, t2)
        val rs1 = MLResourcePool.getAllRecords()
        Assertions.assertEquals(rs1[0].lib_name, t2.lib_name)
        println("Update ${rs1[0]}")

        // delete
        Assertions.assertEquals(true, MLResourcePool.deleteRecord(rs1[0].id))
        println("After deleted: ${MLResourcePool.getAllRecords()}")

    }
}