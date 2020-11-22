/*
 * Copyright 2020 Hochikong
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package references

import java.io.File

fun <R> MutableList<R>.pop(): R? {
    if (this.size > 0) {
        val p = this[this.size - 1]
        this.removeAt(this.size - 1)
        return p
    }
    return null
}

fun File.hasChildDir(): Boolean {
    if (this.isDirectory) {
        return this.listFiles()!!.toList().any { it.isDirectory }
    }
    return false
}

fun File.noChildDir(): Boolean {
    if (this.isDirectory) {
        return this.listFiles()!!.toList().all { !it.isDirectory }
    }
    return false
}

fun <R> MutableList<R>.tlast(): R? {
    return if (this.size == 0) {
        null
    } else {
        this.last()
    }
}

fun File.isEmpty(): Boolean {
    return if (this.isDirectory) {
        this.listFiles()!!.toList().isEmpty()
    } else {
        false
    }
}


fun main() {
    val rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree"

    val ban = mutableListOf<File>()
    val stack = mutableListOf(File(rootPath))
    // root must be dir
    val root = File(rootPath)
    var node: File

    if (root.isEmpty()) {
        ban.add(root)
    } else {
        root.listFiles()!!.toList().map { stack.add(it) }
        node = stack.last()

        while (stack.isNotEmpty()) {
            println(node.toString())
            if (ban.contains(node)) {
                if (node.isDirectory) {
                    if (node.listFiles()!!.toList().any { !ban.contains(it) }) {
                        continue
                    } else {
                        stack.pop()
                        stack.tlast()?.let { node = it } ?: continue
                    }
                }
            }

            if (node.isDirectory) {
                if (node.hasChildDir()) {
                    ban.add(node)
                    for (child in node.listFiles()!!.toList().filter { !ban.contains(it) }) {
                        stack.add(child)
                        node = child
                        break
                    }
                    continue
                }

                if (node.noChildDir()) {
                    ban.add(node)

                    val chi = node.listFiles()!!.toList()
                    if (chi.isEmpty()) {
                        stack.pop()
                        stack.tlast()?.let { node = it } ?: continue
                    } else {
                        chi.map {
                            ban.add(it)
                            println(it.toString())
                        }
                        stack.pop()
                        stack.tlast()?.let { node = it } ?: continue
                    }
                }
            }

            if (node.isFile) {
                ban.add(node)
                stack.pop()
                stack.tlast()?.let { node = it } ?: continue
            }
        }
    }
    println()
    ban.toSet().forEach { println(it) }
}