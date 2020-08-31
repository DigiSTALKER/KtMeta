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

package vertx

import com.fasterxml.jackson.module.kotlin.readValue
import io.github.hochikong.ktmeta.device.*
import io.github.hochikong.ktmeta.predefined.AsyncResultPacket
import io.github.hochikong.ktmeta.predefined.Devices
import io.github.hochikong.ktmeta.predefined.JSONMapper
import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.kotlin.core.eventbus.deliveryOptionsOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDeviceVerticle {
    private val vertx: Vertx = Vertx.vertx()
    private val taskUUIDs = mutableListOf<String>()

    class ReplyHandler : AbstractVerticle() {
        override fun start() {
            vertx.eventBus().consumer<String>("test") { res ->
                val reply = JSONMapper.readValue<AsyncResultPacket>(res.body())

            }
        }
    }

    fun vrequest(
        header: Map<String, String> = DeviceVertHeader,
        addr: String = DeviceVertListenAddr,
        args: DeviceVertUMsg,
    ): String {
        var result = "xxx"
        vertx.eventBus().request<Any>(
            addr,
            JSONMapper.writeValueAsString(args),
            deliveryOptionsOf(headers = header)
        ) { res ->
            result = if (res.succeeded()) {
                println("***${res.result().body()}")
                res.result().body().toString()
            } else {
                println("xxx${res.cause().message}")
                res.cause().message.toString()
            }
        }
        Thread.sleep(500)
        return result
    }

    @Test
    fun testDeviceVerticle() {
        vertx.deployVerticle(ReplyHandler::class.java.name) { promise ->
            if (promise.succeeded()) {
                println("deploy done: ${promise.result()}")
            } else {
                println("deploy failed.")
            }
        }

        vertx.deployVerticle(DeviceVerticle::class.java.name) { promise ->
            if (promise.succeeded()) {
                println("deploy done: ${promise.result()}")
            } else {
                println("deploy failed.")
            }
        }

        // invalid header
        val r = vrequest(header = mapOf("reque" to "nmsl"), args = DeviceVertUMsg("test", "xxx", "dsdad"))
        assertEquals("DeviceVerticle -> Invalid headers -> 'reque: nmsl'", r)

        // invalid action
        val r1 = vrequest(args = DeviceVertUMsg("test", "xxx", "sss"))
        assertEquals("DeviceVerticle -> Invalid task 'xxx' -> 'task: xxx'", r1)

        // invalid arguments
        val r2 = vrequest(args = DeviceVertUMsg("test", "action", "xxxx"))
        assert(r2.contains("DeviceVerticle -> Execute action failed."))

        // get device fail : wrong task
        val r3 = vrequest(
            args = DeviceVertUMsg(
                "test",
                "getDrive",
                "ssdasda"
            )
        )
        assertEquals("DeviceVerticle -> Invalid task 'getDrive' -> 'task: getDrive'", r3)

        // get device
        val r4 = vrequest(
            args = DeviceVertUMsg(
                "test",
                "getDevice",
                JSONMapper.writeValueAsString(
                    DevicePathUUID(
                        "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
                        Devices.LocalDevice
                    )
                )
            )
        )
        val uuid = r4.split("->").last().trim().replace("'", "")
        println("the uuid is $uuid")
        assertEquals("DeviceVerticle -> Create device '$uuid' done. -> '$uuid'", r4)

        // get exists device
        val r5 = vrequest(
            args = DeviceVertUMsg(
                "test",
                "getDevice",
                JSONMapper.writeValueAsString(
                    DevicePathUUID(
                        "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
                        Devices.LocalDevice
                    )
                )
            )
        )
        assertEquals("DeviceVerticle -> Device '$uuid' exists. -> '$uuid'", r5)

        val r6 = vrequest(
            args = DeviceVertUMsg(
                "test",
                "action",
                JSONMapper.writeValueAsString(
                    DeviceAction(
                        uuid = uuid,
                        action = "getFullTree",
                        path = null,
                    )
                )
            )
        )
        taskUUIDs.add(r6.split("->").last().replace("'", "").trim())
        println("Current all task UUIDs are: $taskUUIDs")

        Thread.sleep(5000)
    }
}