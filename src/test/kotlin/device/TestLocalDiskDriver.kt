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

package device/*
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

import io.github.hochikong.ktmeta.device_resources.FileRow
import io.github.hochikong.ktmeta.device_resources.driver.LocalDiskDriver
import io.github.hochikong.ktmeta.predefined.FileType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestLocalDiskDriver {
    private val path = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree"
    private val data = listOf(
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = false,
            fatherPath = ".",
            hasChild = true,
            type = FileType.Directory
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub1",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasChild = true,
            type = FileType.Directory
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub1\\sub11",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub1",
            hasChild = true,
            type = FileType.Directory
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub1\\sub11\\sub1a.txt",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub1\\sub11",
            hasChild = false,
            type = FileType.File
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasChild = true,
            type = FileType.Directory
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub22",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2",
            hasChild = true,
            type = FileType.Directory
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub22\\sub222",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub22",
            hasChild = true,
            type = FileType.Directory
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub22\\sub222\\nmsl.txt",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub22\\sub222",
            hasChild = false,
            type = FileType.File
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub2a.txt",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2",
            hasChild = false,
            type = FileType.File
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\suba.txt",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasChild = false,
            type = FileType.File
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\subb.txt",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasChild = false,
            type = FileType.File
        )
    )


    @Order(1)
    @Test
    fun testSetPath() {
        val obj = LocalDiskDriver()
        assertEquals(false, obj.setTargetDir("./plugins"))
        assertEquals(false, obj.setTargetDir("$path\\nmsl"))
        assertEquals(true, obj.setTargetDir(path))
    }

    @Order(2)
    @Test
    fun testGetTree() {
        val obj = LocalDiskDriver()
        assertEquals(true, obj.setTargetDir(path))
        val rows = obj.getFullTree()
        // File("logs.txt").writeText(rows.map { "$it \n" }.reduce { acc, s -> acc + s })
        assertEquals(data.size, rows.size)
        for (index in data.indices) {
            assertEquals(data[index], rows[index])
        }
        println(obj.initAbsPath)
        obj.dirs.forEach { println(it) }
    }

    @Order(3)
    @Test
    fun testPushPopLsFilter() {
        val list = listOf(
            "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub22",
            "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub2a.txt"
        )
        val x = LocalDiskDriver()
        x.setTargetDir(path)
        assertEquals(true, x.push("sub2"))
        //println(x.dirs)
        println(x.checkStack())
        assertEquals("C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2", x.pwd())
        assertEquals(list, x.ls())
        x.pop()
        println(x.checkStack())
        assertEquals(true, x.push("sub222"))
        val files = x.globFilter(rule = "*.txt")
        assertEquals(
            "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub22\\sub222\\nmsl.txt",
            files[0]
        )
        x.pop()
        // at root dir
        println(x.ls())
        println(x.globFilter("root", "*.txt"))
        val file = x.readFile("C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\subb.txt")
        println(file)
    }
}