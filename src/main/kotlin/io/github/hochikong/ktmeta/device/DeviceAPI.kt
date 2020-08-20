package io.github.hochikong.ktmeta.device

/**
 * All devices should implement this interface.
 * */
interface DeviceAPI {
    fun setTargetDir(path: String): Boolean
    fun getFullTree(): List<FileRow>
    fun cd()
    fun pwd()
    fun readFile()
    fun writeFile()
    fun push()
    fun pop()
}