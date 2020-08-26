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

class Coop : Runnable {
    @Volatile
    private var exit = false

    private var int = 0
    override fun run() {
        while (!exit) {
            try {
                println(int)
                Thread.sleep(1000L)
                int += 1
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    fun close() {
        this.exit = true
    }
}