package io.github.hochikong.ktmeta.device

import io.github.hochikong.ktmeta.predefined.ConvertError
import io.github.hochikong.ktmeta.predefined.FileType
import io.github.hochikong.ktmeta.predefined.PathNotExists
import java.io.File


/**
 * Local disk access API
 * */
class LocalDisk : DeviceAPI {
    private var absPath: String = ""

    /**
     * Set [path] for scanning and check whether [path] exists and legal.
     * [path] should be an absolute path and it must be a directory,
     * and the program can read and write in those paths.
     * */
    override fun setTargetDir(path: String): Boolean {
        val f = File(path)
        if (!f.exists()) return false
        if (!f.isAbsolute) return false
        if (!f.isDirectory) return false
        if (!f.canRead()) return false
        if (!f.canWrite()) return false
        absPath = path
        return true
    }

    /**
     * Return a list of FileRow
     * */
    override fun getFullTree(): List<FileRow> {
        if (absPath.isEmpty()) throw PathNotExists(
            "LocalDisk.getFullTree() said: " +
                    "You must call setTargetDir() first!!"
        )
        val initial = File(this.absPath)
        return scanTree(initial)
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

    private fun hasChild(path: File): Boolean {
        if (!path.exists()) return false
        if (path.listFiles() == null) return false
        if (path.isFile) return false
        if (path.isDirectory) {
            val tmp = path.listFiles()
            if (tmp != null) {
                if (tmp.toList().isNotEmpty()) {
                    return true
                }
            }
        }
        return false
    }

    private fun typeOf(path: File): FileType {
        if (path.isDirectory) return FileType.Directory
        if (path.isFile) return FileType.File
        throw ConvertError("Path Conversion failed.")
    }

    private fun MutableList<FileRow>.addFileRow(path: File, rootDir: File) {
        val fatherPath = path.parent.toString()
        this.add(
            fileRowBuilder(
                path.toString(),
                rootDir.toString(),
                fatherPath.isNotEmpty(),
                if (fatherPath.isNotEmpty()) fatherPath else ".",
                hasChild(path),
                typeOf(path)
            )
        )
    }

    private fun MutableList<File>.pop(): File? {
        if (this.isNotEmpty()) {
            val r = this[this.size - 1]
            this.removeAt(this.size - 1)
            return r
        }
        return null
    }

    fun scanTree(rootDir: File): List<FileRow> {
        // scan
        /*fun fileRowBuilder(
            selfPath: String,
            rootPath: String,
            hasFather: Boolean,
            fatherPath: String,
            hasChild: Boolean,
            type: FileType
        )*/
        // add rootPath
        val resultList = mutableListOf<FileRow>()


        return resultList.toList()
    }
}