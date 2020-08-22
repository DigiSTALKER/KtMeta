package io.github.hochikong.ktmeta.device

/**
 * All devices should implement this interface.
 * */
interface DeviceAPI {
    fun setTargetDir(path: String): Boolean
    fun getFullTree(): List<FileRow>
    fun pwd(): String
    fun ls(dirname: String? = null): List<String>
    fun push(dirname: String): Boolean
    fun pop(): String
}