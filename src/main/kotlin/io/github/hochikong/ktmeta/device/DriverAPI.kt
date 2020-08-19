package io.github.hochikong.ktmeta.device

/**
 * All devices should implement this interface.
 * */
interface DriverAPI {
    fun setTargetDir(path: String): Boolean
    fun getFullTree()
    fun cd()
    fun pwd()
    fun readFile()
    fun writeFile()
    fun push()
    fun pop()
}