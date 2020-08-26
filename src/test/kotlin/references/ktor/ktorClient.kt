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

package references.ktor

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println(request())
    }
}

suspend fun request(): String {
    val cli = HttpClient()
    val r: String = cli.get("http://localhost:8000")
    cli.close()
    return r
}