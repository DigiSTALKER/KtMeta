package io.github.hochikong.ktmeta.references

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