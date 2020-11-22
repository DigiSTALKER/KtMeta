import java.util.jar.JarFile

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

fun main() {
    val path = "plugins/ktmeta-basic-1.0-SNAPSHOT.jar"
    val absp = "file:C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\plugins\\ktmeta-basic-1.0-SNAPSHOT.jar"
    val m = JarFile(path).manifest
    val ma = m.mainAttributes
    val classNames = ma.keys.filter { it.toString().startsWith("PluginRootName") }.map { ma[it] }.toList()
    println(classNames)
}
