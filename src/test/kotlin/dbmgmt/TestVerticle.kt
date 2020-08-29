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

package dbmgmt

import io.github.hochikong.ktmeta.dbmgmt.*
import io.github.hochikong.ktmeta.predefined.Encryption
import io.github.hochikong.ktmeta.predefined.JSONMapper
import io.github.hochikong.ktmeta.predefined.SupportedDBs
import io.vertx.core.Vertx
import io.vertx.kotlin.core.eventbus.deliveryOptionsOf
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.File

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestVerticle {
    private val vertx = Vertx.vertx()
    private val username = "crrtt"
    private val password = "xoof24io6598!$%$%ds*$"

    companion object {
        @JvmStatic
        @AfterAll
        @BeforeAll
        fun cleanUp() {
            println(File(".").canonicalPath)
            Maintainer.closeConnection()
            val f = File("./dbreg.db")
            f.delete()
        }
    }

    @Test
    @Order(1)
    fun deployVerticle() {
        vertx.deployVerticle(DBVerticle::class.java.name) { promise ->
            if (promise.succeeded()) {
                println("deploy done, id: ${promise.result()}")
            } else {
                println("deploy failed")
            }
        }

        // invalid header
        vertx.eventBus().request<String>(
            DBVertListenAddr,
            JSONMapper.writeValueAsString(DBVertUMsg("test", "sb", "nmsl")),
            deliveryOptionsOf(headers = mapOf("request" to "ktmeta.dbmgmt.ERROR"))
        ) { res ->
            assertEquals(
                "DBVerticle; Invalid headers; request: ktmeta.dbmgmt.ERROR",
                res.cause().message?.trim()
            )
            println("invalid headers: ${res.cause().message}")
        }

        // invalid task
        vertx.eventBus().request<String>(
            DBVertListenAddr,
            JSONMapper.writeValueAsString(DBVertUMsg("test", "sb", "nmsl")),
            deliveryOptionsOf(headers = DBVertHeader)
        ) { res ->
            assertEquals(
                "DBVerticle; Illegal task 'sb' in message.; failed",
                res.cause().message?.trim()
            )
            println("invalid task: ${res.cause().message}")
        }

        // add db
        vertx.eventBus().request<String>(
            DBVertListenAddr,
            JSONMapper.writeValueAsString(
                DBVertUMsg(
                    "test",
                    "addDatabase",
                    JSONMapper.writeValueAsString(
                        AddDatabase(
                            type = SupportedDBs.SQLite,
                            name = "testdb1",
                            desc = "desc of testdb1",
                            user = Encryption.encrypt(username),
                            password = Encryption.encrypt(password),
                            url = "testdb1.db"
                        )
                    )
                )
            ),
            deliveryOptionsOf(headers = DBVertHeader)
        ) { res ->
            assertEquals("DBVerticle; Add database 'testdb1' done.; testdb1", res.result().body().trim())
            println("add succeeded: ${res.result().body()}")
        }

        // add again, raise exception
        vertx.eventBus().request<String>(
            DBVertListenAddr,
            JSONMapper.writeValueAsString(
                DBVertUMsg(
                    "test",
                    "addDatabase",
                    JSONMapper.writeValueAsString(
                        AddDatabase(
                            type = SupportedDBs.SQLite,
                            name = "testdb1",
                            desc = "desc of testdb1",
                            user = Encryption.encrypt(username),
                            password = Encryption.encrypt(password),
                            url = "testdb1.db"
                        )
                    )
                )
            ),
            deliveryOptionsOf(headers = DBVertHeader)
        ) { res ->
            assertEquals("DBVerticle; Add database 'testdb1' failed.; testdb1", res.cause().message?.trim())
            if (res.failed()) {
                println("add failed: because not null constrain ${res.cause().message}")
            }
        }

        // grant db done
        vertx.eventBus().request<String>(
            DBVertListenAddr,
            JSONMapper.writeValueAsString(
                DBVertUMsg(
                    "test",
                    "grantDB",
                    JSONMapper.writeValueAsString(GrantDB("testdb1", "JDBC", username, password))
                )
            ),
            deliveryOptionsOf(headers = DBVertHeader)
        ) { res ->
            if (res.succeeded()) {
                println(res.result().body())
            } else {
                println(res.cause().message)
            }
        }

        // wait the vertx
        Thread.sleep(3000)

    }
}
