package io.github.hochikong.ktmeta.references

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val dir = Paths.get("C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree")
    Files.walk(dir).use { s ->
        s.forEach { println(it) }
    }
}