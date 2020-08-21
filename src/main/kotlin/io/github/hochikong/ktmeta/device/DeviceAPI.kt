package io.github.hochikong.ktmeta.device

/**
 * All devices should implement this interface.
 * */
interface DeviceAPI {
    fun setTargetDir(path: String): Boolean
    fun getFullTree(): List<FileRow>
    fun pwd(): String
    fun readFile(path: String)
    fun writeFile(path: String)
    fun push(path: String): Boolean
    fun pop(): String
}