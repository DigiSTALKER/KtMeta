package io.github.hochikong.ktmeta.device

import java.io.File


/**
 * Local disk access API
 * */
class LocalDisk : DriverAPI {
    private lateinit var absPath: String

    override fun setTargetDir(path: String): Boolean {
        val f = File(path)
        if (!f.isAbsolute) return false
        if (!f.canRead()) return false
        if (!f.canWrite()) return false
        if (!f.isDirectory) return false
        absPath = path
        return true
    }

    override fun getFullTree() {
        TODO("Not yet implemented")
    }

    override fun cd() {
        TODO("Not yet implemented")
    }

    override fun pwd() {
        TODO("Not yet implemented")
    }

    override fun readFile() {
        TODO("Not yet implemented")
    }

    override fun writeFile() {
        TODO("Not yet implemented")
    }

    override fun push() {
        TODO("Not yet implemented")
    }

    override fun pop() {
        TODO("Not yet implemented")
    }
}