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

import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.vertx.core.AbstractVerticle

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

class ConsumePrint : AbstractVerticle() {
    private val server = embeddedServer(Netty, 8020, module = Application::module2)
    private val worker = LoopPrint()
    private var name: String = ""
    private val stack = mutableListOf<String>()

    override fun start() {
        vertx.eventBus().consumer<String>("url1") { msg ->
            when {
                msg.body() == "launch" -> {
                    server.start()
                    Thread(worker).apply { start() }
                    vertx.eventBus().send("url3", "launch done")
                }
                msg.body() == "stop" -> {
                    println("Closing server")
                    server.stop(10, 10)
                    worker.close()
                }
                msg.body() == "simpleLoop" -> {
                    vertx.executeBlocking<String>({ future ->
                        run {
                            var d = 0.0
                            for (i in 0..10000000) d += (i * 10 / 23 * 10.02368899)
                            future.complete(d.toString())
                        }
                    }) { res -> println(" !!!!!!!! $res") }
                }
                msg.body() == "changeName" -> {
                    val context = vertx.orCreateContext
                    context.put("key", "This is value")
                    context.runOnContext {
                        println(context.get("key") as String)
                    }
                }
                msg.body() == "addStack" -> {
                    vertx.executeBlocking<String>({ promise ->
                        run {
                            Thread.sleep(10000)
                            promise.complete(if (stack.isNotEmpty()) stack.last() else "stack 1")
                        }
                    }) { res ->
                        run {
                            stack.add(res.result().toString())
                            vertx.eventBus().send("url3", "I received your msg!!")
                            println(stack)
                        }
                    }
                }
                msg.body() == "Stack" -> {
                    vertx.eventBus().send("url3", "stack is $stack")
                }
                else -> println("1 received message.body is ${msg.body()}")
            }
        }
    }
}

class LoopPrint : Runnable {
    @Volatile
    private var exit = false

    private var int = 0
    override fun run() {
        while (!exit) {
            try {
                println(int)
                Thread.sleep(2000L)
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