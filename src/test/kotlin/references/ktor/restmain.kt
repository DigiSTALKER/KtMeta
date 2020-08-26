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

import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

class RestMain : Runnable {
    private val server = embeddedServer(Netty, 8020, module = Application::module2)

    @Volatile
    private var exit = false

    fun close() {
        this.exit = true
    }

    override fun run() {
        server.start()
        while (!exit) {
            try {
                Thread.sleep(10L)
            } catch (e: InterruptedException) {
                println(e.printStackTrace())
            }
        }
        println("Kill the server.")
    }
}