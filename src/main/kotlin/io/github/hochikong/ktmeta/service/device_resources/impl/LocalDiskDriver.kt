/*
 * Copyright 2020 Hochikong
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.hochikong.ktmeta.service.device_resources.impl

import io.github.hochikong.ktmeta.predefined.ConvertError
import io.github.hochikong.ktmeta.predefined.FileType
import io.github.hochikong.ktmeta.service.device_resources.DeviceAPI
import io.github.hochikong.ktmeta.service.device_resources.FileRow
import io.github.hochikong.ktmeta.service.device_resources.fileRowBuilder
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


/**
 * Local disk access API
 * */
class LocalDiskDriver : DeviceAPI {
    // absPath must be directory
    var initAbsPath: String = "."
        private set

    // path and its father path
    var dirs: Map<String, String> = mapOf()
        private set

    // path stack
    private var pathStack: MutableList<String> = mutableListOf()
    fun checkStack(): MutableList<String> {
        return this.pathStack
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
        initAbsPath = path
        pathStack.add(path)
        scanTree(Paths.get(this.initAbsPath))
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
                        rootPath = this.initAbsPath,
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
            .forEach {
                when {
                    it.fatherPath != "." ->
                        tmp[it.selfPath.replace("${it.fatherPath}\\", "")] = it.fatherPath
                    else ->
                        tmp["root"] = it.selfPath
                }
            }

        this.dirs = tmp.toMap()

        return result.toList()
    }

    /**
     * Return a list of FileRow.
     * */
    override fun getFullTree(): List<FileRow> {
        return scanTree(Paths.get(this.initAbsPath))
    }

    /**
     * Return current abs path.
     * */
    override fun pwd(): String {
        return pathStack.last().toString()
    }

    /**
     * Just provide directory name as [dirname] to ls().
     * */
    override fun ls(dirname: String?): List<String> {
        val queryAbsPath = if (dirname != null) pathSearch(dirname)[0] else pathStack.last()
        return if (queryAbsPath.isNotEmpty()) {
            Files.newDirectoryStream(Paths.get(queryAbsPath)).use { stream ->
                stream.map { it.toString() }
                    .toList()
            }
        } else {
            listOf()
        }
    }

    /**
     * Return all files in dir [dirname] or in last element of current path.
     * */
    fun globFilter(dirname: String? = null, rule: String): List<String> {
        val queryAbsPath = if (dirname != null) pathSearch(dirname)[0] else pathStack.last()
        return if (queryAbsPath.isNotEmpty()) {
            Files.newDirectoryStream(Paths.get(queryAbsPath), rule).use { stream ->
                stream.map { it.toString() }
                    .toList()
            }
        } else {
            listOf()
        }
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

    /**
     * Goto [dirname] dir by directory's name.
     * */
    override fun push(dirname: String): Boolean {
        val queryR = pathSearch(dirname)
        if (queryR.isNotEmpty()) {
            pathStack.add(queryR[0])
            return true
        }
        return false
    }

    /**
     * Search [dirname] exists or not. If exists return its abs path.
     * */
    private fun pathSearch(dirname: String): List<String> {
        if (this.dirs.isEmpty()) scanTree(Paths.get(this.initAbsPath))

        return if (dirs.keys.any { it.contains(dirname) }) {
            val r = dirs.keys
                .filter { it.contains(dirname) }
                .filter { it.replace(dirname, "").isBlank() }
                .map {
                    when (it) {
                        "root" -> this.initAbsPath
                        else -> "${dirs[it]}\\$dirname"
                    }
                }.toList()
            // println("search result are: $r")
            r
        } else {
            listOf()
        }
    }

    /**
     * Return to former directory.
     * */
    override fun pop(): String {
        return this.pathStack.pop() ?: initAbsPath
    }

    private fun typeOf(path: Path): FileType {
        if (Files.isDirectory(path)) return FileType.Directory
        if (Files.isRegularFile(path)) return FileType.File
        throw ConvertError("Path Conversion failed.")
    }


    override fun readFile(path: String): String {
        val file = Paths.get(path)
        var empty = "empty"
        if (Files.notExists(file) && !Files.exists(file)) return empty
        if (file.toAbsolutePath() != file && file.toRealPath() != file) return empty
        if (!Files.isRegularFile(file)) return empty
        if (!Files.isReadable(file)) return empty
        try {
            val bytes = Files.readAllBytes(file)
            empty = String(bytes)
        } catch (e: IOException) {
            println(e.toString())
            println(e.printStackTrace())
        }
        return empty
    }
}