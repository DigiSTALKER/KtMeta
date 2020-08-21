package io.github.hochikong.ktmeta.device

import io.github.hochikong.ktmeta.predefined.ConvertError
import io.github.hochikong.ktmeta.predefined.FileType
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.math.abs


/**
 * Local disk access API
 * */
class LocalDisk(root: String) : DeviceAPI {
    constructor() : this("")

    // absPath must be directory
    var absPath: String = root
        private set

    // path and its father path
    var dirs: Map<String, String> = mapOf()
        private set

    // path stack
    private var pathStack: MutableList<String> = mutableListOf()
    fun checkStack() {
        println(this.pathStack)
    }

    /**
     * Set [path] for scanning and check whether [path] exists and legal.
     * [path] should be an absolute path and it must be a directory,
     * and the program can read and write in those paths.
     * */
    override fun setTargetDir(path: String): Boolean {
        val f = Paths.get(path)
        if (Files.notExists(f) && !Files.exists(f)) return false
        if (f.toAbsolutePath() != f && f.toRealPath() != f) return false
        if (Files.isRegularFile(f)) return false
        if (!Files.isDirectory(f)) return false
        if (!Files.isReadable(f)) return false
        if (!Files.isWritable(f)) return false
        absPath = path
        pathStack.add(path)
        return true
    }

    /**
     * fileRowBuilder ->
     *  val selfPath: String,
     *  val rootPath: String,
     *  val hasFather: Boolean,
     *  val fatherPath: String,
     *  val hasChild: Boolean,
     *  val type: FileType
     * */
    private fun scanTree(dir: Path): List<FileRow> {
        val result = mutableListOf<FileRow>()
        Files.walk(dir).use { stream ->
            stream.forEach {
                result.add(
                    fileRowBuilder(
                        selfPath = it.toString(),
                        rootPath = this.absPath,
                        hasFather = it.parent.toString().isNotEmpty(),
                        fatherPath = it.parent.toString(),
                        hasChild = when {
                            Files.isDirectory(it) -> when {
                                Files.newDirectoryStream(it).toList().isNotEmpty() -> true
                                else -> false
                            }
                            else -> false
                        },
                        type = typeOf(it)
                    )
                )
            }
        }

        val tmp = mutableMapOf<String, String>()
        result.filter { it.type == FileType.Directory }
            .forEach { tmp[it.selfPath.replace(it.fatherPath, "father")] = it.fatherPath }

        this.dirs = tmp.toMap()

        return result.toList()
    }

    /**
     * Return a list of FileRow
     * */
    override fun getFullTree(): List<FileRow> {
        return scanTree(Paths.get(this.absPath))
    }

    override fun pwd(): String {
        return pathStack.last()
    }

    override fun readFile(path: String) {
        TODO("Not yet implemented")
    }

    override fun writeFile(path: String) {
        TODO("Not yet implemented")
    }

    private fun MutableList<String>.pop(): String? {
        return if (this.size > 1) {
            val v = this[this.size - 1]
            this.removeAt(this.size - 1)
            v
        } else {
            null
        }
    }

    override fun push(path: String): Boolean {
        if (this.dirs.isEmpty()) scanTree(Paths.get(this.absPath))

        if (dirs.keys.any { it.contains(path) }) {
            val values =
                dirs.keys.filter { it.startsWith("father\\") && it.contains(path) }.map { "${dirs[it]}\\$it" }.toList()

            pathStack.add(values[0])
            return true
        }
        return false
    }

    override fun pop(): String {
        return this.pathStack.pop() ?: absPath
    }

    private fun typeOf(path: Path): FileType {
        if (Files.isDirectory(path)) return FileType.Directory
        if (Files.isRegularFile(path)) return FileType.File
        throw ConvertError("Path Conversion failed.")
    }
}