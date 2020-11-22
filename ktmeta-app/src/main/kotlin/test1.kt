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

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
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

class Waste : AbstractVerticle(){
    private val mapper = ObjectMapper().registerModule(KotlinModule())

    override fun start() {
//        vertx.eventBus().addInboundInterceptor<String> { msg ->
//            println("Inbound !!!!! ${msg.body()}")
//        }
        vertx.eventBus().localConsumer<String>("url2") { msg ->
//            if (msg.body() == "waste"){
//                Thread.sleep(10000)
//                println("Im wake up.")
//            }
            val header = msg.headers()
            when(header.get("msg")){
                "waste" -> {
                    println("from url2: ${mapper.readValue<Person>(msg.body())}")
                    msg.reply("you are wasting")
                }
                else -> {
                    println("Waste wrong")
                    msg.fail(23, "Waste wrong reply")
                }
            }
        }
    }
}