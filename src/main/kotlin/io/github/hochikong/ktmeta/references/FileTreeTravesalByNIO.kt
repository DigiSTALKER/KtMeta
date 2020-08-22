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

package io.github.hochikong.ktmeta.references

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val dir = Paths.get("C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree")
    Files.walk(dir).use { s ->
        s.forEach { println(it) }
    }
}