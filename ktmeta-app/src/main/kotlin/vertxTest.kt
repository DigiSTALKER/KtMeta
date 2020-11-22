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

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import java.util.*
import kotlin.random.Random
import kotlin.system.exitProcess

class Wok : AbstractVerticle() {
    override fun start() {
        vertx.eventBus().consumer<String>("Wok") { msg ->
            RefObj.maps[msg.body()] = Random.nextInt(0, 1000)
        }
    }
}

fun main() {
    val v = Vertx.vertx()
    v.deployVerticle(Wok::class.java.name) { res ->
        run {
            if (res.succeeded()) {
                println("Deployment id is: ${res.result()}")
            } else {
                println("Deployment failed!")
            }
        }
    }
    while (true){
        val inputA = Scanner(System.`in`).nextLine().trim()
        if (inputA != "exit") {
            v.eventBus().send("Wok", inputA)
            Thread.sleep(100)
            println("you input is $inputA, ref is ${RefObj.maps[inputA]}")
        }else{
            exitProcess(0)
        }
    }
}