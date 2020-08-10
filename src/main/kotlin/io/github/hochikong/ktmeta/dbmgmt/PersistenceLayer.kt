/**
 * @author Hochikong
 * Persistence layer
 * */
package io.github.hochikong.ktmeta.dbmgmt

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File


/**
 * Save database registration as json to absPath/dbregist.json file or load to data class.
 */
object DBRegPersistence {
    private val mapper = ObjectMapper().registerKotlinModule()

    private fun returnPath(absPath: String, fileName: String): String {
        return if ((absPath.endsWith("\\")) or (absPath.endsWith("/"))) {
            absPath + fileName
        } else {
            when {
                absPath.contains("\\") -> "$absPath\\$fileName"
                absPath.contains("/") -> "$absPath/$fileName"
                else -> "./$fileName"
            }
        }
    }

    /**
     * Load dbreg file from json
     * @param absPath String, Simple absolute path without filename.
     * @param fileName String, by default is dbregist.json.
     * @return Boolean
     * */
    fun loadFrom(absPath: String, fileName: String = "dbregist.json"): Boolean {
        val f = File(returnPath(absPath, fileName))
        return when {
            f.exists() and f.isFile and f.canRead() -> {
                val result = mapper.readValue<DBRegJSONContainer>(f.readText())

                true
            }
            else -> {
                false
            }
        }
    }

    /**
     * Save db registration info to json file or overwrite it. You can create multiple files.
     * @param absPath String, simple absolute path without filename.
     * @param fileName String, file name.
     * @param data DBRegJSONContainer with data.
     * @return Boolean
     * */
    fun saveTo(absPath: String, fileName: String = "dbregist.json", data: DBRegJSONContainer): Boolean {
        val f = File(returnPath(absPath, fileName))
        val jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data)
        return when {
            f.exists() and f.isFile and f.canWrite() -> {
                f.writeText(jsonStr)
                true
            }
            !f.exists() -> {
                File(absPath).mkdirs()
                f.writeText(jsonStr)
                true
            }
            else -> false
        }
    }

    /**
     * Search db configurations
     * */
}