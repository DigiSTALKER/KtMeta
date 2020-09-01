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

package io.github.hochikong.ktmeta.device

/**
 * All devices should implement this interface.
 *
 * You can check my LocalDiskDriver.kt for reference.
 *
 * # Explanations:
 *
 * ## Parameters:
 *
 * path -> It means absolute path(local filesystem) or a custom string as path(extension devices).
 *
 * dirname -> Just the name of a directory, e.g., path "C:\Users\jack" -> dir name is "jack".
 *
 * ## Functions:
 *
 * setTargetDir(path) -> Used to set root dir for the device. Your classes should check the path is valid or not.
 *
 * getFullTree() -> Return a list of FileRow, which contains all dirs and files as FileRow instances.
 *
 * pwd() -> Return current absolute path.
 *
 * ls(dirname) -> Return all dirs and files' abs path in current path(when dirname is null) or in dirname.
 *
 * push(dirname) -> Push into dirname.
 *
 * pop() -> Return to former dir.
 * */
interface DeviceAPI {
    fun setTargetDir(path: String): Boolean
    fun getFullTree(): List<FileRow>
    fun pwd(): String
    fun ls(dirname: String? = null): List<String>
    fun push(dirname: String): Boolean
    fun pop(): String
}