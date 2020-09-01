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

package io.github.hochikong.ktmeta.device

import com.fasterxml.jackson.module.kotlin.readValue
import io.github.hochikong.ktmeta.predefined.AsyncResultPacket
import io.github.hochikong.ktmeta.predefined.JSONMapper
import io.github.hochikong.ktmeta.predefined.ResultMsg
import io.github.hochikong.ktmeta.predefined.VertListLoads
import io.vertx.core.AbstractVerticle
import io.vertx.core.shareddata.LocalMap
import java.util.*

class DeviceVerticle : AbstractVerticle() {
    private val deviceCFGs = mutableMapOf<String, DevicePathUUID>()
    private val deviceObjs = mutableMapOf<String, DeviceAPI>()

    override fun start() {
        val shareDataInstance = vertx.sharedData()
        val sharedMap: LocalMap<String, String> = shareDataInstance.getLocalMap(DeviceSharedMapName)

        vertx.eventBus().consumer<String>(DeviceVertListenAddr) { msg ->
            val uMSGDataClass: DeviceVertUMsg
            when (msg.headers().get("request")) {
                DeviceVertHeader["request"] -> {
                    uMSGDataClass = JSONMapper.readValue(msg.body())

                    // analyze DeviceVertUMsg task
                    val result = executeMgmtTask(uMSGDataClass, sharedMap)
                    if (!result.succeeded) {
                        msg.fail(-1, "DeviceVerticle -> ${result.msg} -> '${result.result}'")
                    } else {
                        msg.reply("DeviceVerticle -> ${result.msg} -> '${result.result}'")
                    }

                }
                else -> msg.fail(-1, "DeviceVerticle -> Invalid headers -> '${msg.headers().toString().trim()}'")
            }
        }
    }

    private fun executeMgmtTask(uMSGDataClass: DeviceVertUMsg, shareDataMap: LocalMap<String, String>): ResultMsg {
        return when (uMSGDataClass.task) {
            "getDevice" -> {
                // this condition only add cfg into deviceCFGs
                val uuidDataClass = JSONMapper.readValue<DevicePathUUID>(uMSGDataClass.arguments)
                val uuid = uuidDataClass.toUUID()

                if (deviceCFGs.containsKey(uuid)) {
                    ResultMsg(true, uuid, "Device '$uuid' exists.")
                } else {
                    try {
                        DeviceMgmt.getDevice(uuidDataClass.deviceType.identity)
                        // add cfg only
                        deviceCFGs[uuid] = uuidDataClass
                        println("add done")
                        ResultMsg(true, uuid, "Create device '$uuid' done.")
                    } catch (e: Exception) {
                        ResultMsg(false, e.toString(), "Execute action failed.")
                    }
                }
            }

            "removeDevice" -> {
                try {
                    val uuidDataClass = JSONMapper.readValue<DevicePathUUID>(uMSGDataClass.arguments)
                    val uuid = uuidDataClass.toUUID()
                    if (deviceObjs.containsKey(uuid)) {
                        deviceObjs.remove(uuid)
                        deviceCFGs.remove(uuid)
                        ResultMsg(true, uuid, "Remove device '$uuid' done.")
                    } else {
                        ResultMsg(false, uuid, "Remove device '$uuid failed. No such device.")
                    }
                } catch (e: Exception) {
                    ResultMsg(false, e.toString(), "Execute action failed.")
                }
            }

            "action" -> {
                try {
                    val actionDC: DeviceAction = JSONMapper.readValue(uMSGDataClass.arguments)
                    val uuid = actionDC.uuid
                    val path = actionDC.path
                    executeAction(uuid, actionDC, path, uMSGDataClass.from, shareDataMap)
                } catch (e: Exception) {
                    ResultMsg(false, e.toString(), "Execute action failed.")
                }

            }
            else -> ResultMsg(false, "task: ${uMSGDataClass.task}", "Invalid task '${uMSGDataClass.task}'")
        }
    }

    private fun executeAction(
        uuid: String,
        actionDataClass: DeviceAction,
        path: String?,
        sendToWho: String,
        shareDataMap: LocalMap<String, String>
    ): ResultMsg {
        // when no such device configuration
        if (deviceCFGs[uuid] == null) {
            return ResultMsg(false, uuid, "No such device. You should getDevice first.")
        } else {
            val currentDevice: DeviceAPI
            // has configuration, then check the device exists or not.
            if (deviceObjs[uuid] == null) {
                // create new device and add
                val newDevice = DeviceMgmt.getDevice(deviceCFGs[uuid]!!.deviceType.identity)
                val targetPath = deviceCFGs[uuid]!!.targetDir
                if (!newDevice.setTargetDir(targetPath)) {
                    return ResultMsg(
                        false,
                        deviceCFGs[uuid]!!.targetDir,
                        "Set '$targetPath' as target path failed. Call setTargetDir() before any other actions."
                    )
                }

                deviceObjs[uuid] = newDevice
                currentDevice = newDevice
            } else {
                // get the device from deviceObjs
                currentDevice = deviceObjs[uuid]!!
            }

            // check actions
            when (actionDataClass.action) {
                // non blocking codes.
                "setTargetDir" -> {
                    val realPath = if (path != null) {
                        if (path.isNotBlank()) path else deviceCFGs[uuid]!!.targetDir
                    } else {
                        deviceCFGs[uuid]!!.targetDir
                    }
                    val result: Boolean = currentDevice.setTargetDir(realPath)
                    return if (result) {
                        ResultMsg(true, realPath, "Set '$realPath' as root dir.")
                    } else {
                        ResultMsg(false, realPath, "Path '$realPath' set failed.")
                    }
                }
                "pwd" -> {
                    val currentPath = currentDevice.pwd()
                    return ResultMsg(true, currentPath, "Current path: $currentPath")
                }
                "ls" -> {
                    val elements: List<String> = currentDevice.ls(path)
                    return ResultMsg(
                        true,
                        JSONMapper.writeValueAsString(VertListLoads(elements)),
                        "Use VertListLoads to parse data."
                    )
                }
                "push" -> {
                    return if (path == null) {
                        ResultMsg(false, "empty path", "'null' as parameter for push is invalid.")
                    } else {
                        val b = currentDevice.push(path)
                        if (b) {
                            ResultMsg(true, currentDevice.pwd(), "Current path is: '${currentDevice.pwd()}'.")
                        } else {
                            ResultMsg(false, currentDevice.pwd(), "Can't push into '$path'.")
                        }
                    }
                }
                "pop" -> {
                    val b = currentDevice.pop()
                    return ResultMsg(true, b, "Pop done, current path is: '${currentDevice.pwd()}'")

                }

                // may blocking code.
                "getFullTree" -> {
                    val taskUUID: String =
                        UUID.nameUUIDFromBytes("${actionDataClass.action} ${actionDataClass.path}".toByteArray())
                            .toString()
                    vertx.executeBlocking<String>({ task ->
                        val result: List<FileRow> = currentDevice.getFullTree()
                        val fileRows: List<String> = result.map { JSONMapper.writeValueAsString(it) }.toList()
                        val asyncOutput: String = JSONMapper.writeValueAsString(
                            AsyncResultPacket(
                                taskUUID = taskUUID,
                                from = DeviceVertListenAddr,
                                loads = JSONMapper.writeValueAsString(VertListLoads(data = fileRows)),
                                decoder = "VertListLoads"
                            )
                        )
                        task.complete(asyncOutput)
                    }) { handler ->
                        // write data on localMap
                        shareDataMap[taskUUID] = handler.result()
                        // notify receiver
                        vertx.eventBus().send(sendToWho, "$taskUUID done.")
                    }
                    return ResultMsg(true, taskUUID, "You async reply uuid is '$taskUUID'")
                }


                else -> {
                    return ResultMsg(false, actionDataClass.action, "Invalid action '${actionDataClass.action}'")
                }
            }
        }
    }
}