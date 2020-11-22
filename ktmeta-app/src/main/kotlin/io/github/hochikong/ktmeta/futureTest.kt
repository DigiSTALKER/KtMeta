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

package io.github.hochikong.ktmeta

import io.vertx.core.*
import java.util.*
import kotlin.system.exitProcess

class ComposeExample : AbstractVerticle() {
    override fun start() {
        val future = anAsyncAction()
        future.compose { name: String -> anotherAsyncAction(name) }
            .onComplete { ar: AsyncResult<String> ->
                if (ar.failed()) {
                    println("Something bad happened")
                    ar.cause().printStackTrace()
                } else {
                    println("Result: " + ar.result())
                }
            }
    }

    private fun anAsyncAction(): Future<String> {
        val promise = Promise.promise<String>()
        // mimic something that take times
        vertx.setTimer(1000) { promise.complete("world") }
        return promise.future()
    }

    private fun anotherAsyncAction(name: String): Future<String> {
        val promise = Promise.promise<String>()
        // mimic something that take times
        vertx.setTimer(100) { promise.complete("hello $name") }
        return promise.future()
    }
}

fun main() {
    val vert = Vertx.vertx()
    vert.deployVerticle(ComposeExample::class.java.name)
    println("enter to exit...")
    Scanner(System.`in`).nextLine()
    exitProcess(0)
}