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

package io.github.hochikong.ktmeta.dbmgmt

import com.fasterxml.jackson.module.kotlin.readValue
import io.github.hochikong.ktmeta.predefined.Encryption
import io.github.hochikong.ktmeta.predefined.JSONMapper
import io.vertx.core.AbstractVerticle

class DBVerticle : AbstractVerticle() {
    override fun start() {
        vertx.eventBus().consumer<String>(DBVertListenAddr) { msg ->
            val uMSGDataClass: DBVertUMsg
            when (msg.headers().get("request")) {
                DBVertHeader["request"] -> {
                    uMSGDataClass = JSONMapper.readValue(msg.body())

                    val result = executeMgmtTask(uMSGDataClass)
                    // println(result)

                    if (!result.succeeded) {
                        msg.fail(-1, "DBVerticle; ${result.msg}; ${result.result}")
                    } else {
                        msg.reply("DBVerticle; ${result.msg}; ${result.result}")
                    }

                }
                else -> msg.fail(1, "DBVerticle; Invalid headers; ${msg.headers()}")
            }
        }
    }

    private fun executeMgmtTask(uMSGDataClass: DBVertUMsg): ResultMsg {
        return when (uMSGDataClass.task) {
            "addDatabase" -> {
                val args = JSONMapper.readValue<AddDatabase>(uMSGDataClass.arguments)
                return if (DBMgmt.addDatabase(
                        type = args.type,
                        name = args.name,
                        desc = args.desc,
                        user = args.user,
                        password = args.password,
                        url = args.url
                    )
                ) {
                    ResultMsg(true, args.name, "Add database '${args.name}' done.")
                } else {
                    ResultMsg(false, args.name, "Add database '${args.name}' failed.")
                }
            }

            "removeDatabase" -> {
                val args = JSONMapper.readValue<RemoveDatabase>(uMSGDataClass.arguments)
                return if (DBMgmt.removeDatabase(args.name)) {
                    ResultMsg(true, args.name, "Remove database '${args.name}' done.")
                } else {
                    ResultMsg(false, args.name, "Remove database '${args.name}' failed.")
                }
            }

            "queryReg" -> {
                val args = uMSGDataClass.arguments
                if (args == "QueryReg") {
                    return if (DBMgmt.queryReg()) {
                        ResultMsg(true, "done", "Update catalog done.")
                    } else {
                        ResultMsg(false, "failed", "Update catalog failed.")
                    }
                } else {
                    ResultMsg(false, "failed", "Illegal task '${args.toLowerCase()}' in message.")
                }
            }

            "checkCatalog" -> {
                val args = uMSGDataClass.arguments
                if (args == "CheckCatalog") {
                    val catalog = DBMgmt.checkCatalog().last()
                    return if ((catalog as Set<*>).isNotEmpty()) {
                        ResultMsg(true, catalog.toString(), "Check catalog done.")
                    } else {
                        ResultMsg(false, "failed", "Check catalog failed because it is empty.")
                    }
                } else {
                    ResultMsg(false, "failed", "Illegal task '${args.toLowerCase()}' in message.")
                }
            }

            "grantDB" -> {
                val args = JSONMapper.readValue<GrantDB>(uMSGDataClass.arguments)
                DBMgmt.queryReg()
                val query: RegRow? = DBRegCatalog[args.name]
                if (query != null) {
                    val r = Encryption.verify(args.user, query.user)
                    val r1 = Encryption.verify(args.password, query.password)
                    if (r1 && (r1 == r)) {
                        ResultMsg(true, args.name, "Grant access on database '${args.name}' done.")
                    } else {
                        ResultMsg(
                            false, args.name, "Grant access on database '${args.name}' failed, " +
                                    "invalid username or password."
                        )
                    }
                } else {
                    ResultMsg(
                        false, args.name, "Grant access on database '${args.name}' failed, " +
                                "no such database."
                    )
                }
            }

            else -> ResultMsg(false, "failed", "Illegal task '${uMSGDataClass.task}' in message.")
        }
    }
}

/**
 * Used by executeMgmtTask
 * */
data class ResultMsg(val succeeded: Boolean, val result: Any, val msg: String)