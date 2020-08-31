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

import io.github.hochikong.ktmeta.predefined.Devices
import java.util.*

/**
 * Actor listen address
 * */
const val DeviceVertListenAddr = "ktmeta.dbmgmt.DeviceMgmt"

/**
 * DeviceVerticle message
 * @param from Who send this message
 * @param task Special string for different tasks: getDevice, removeDevice, action.
 * @param arguments Argument for loading different device.
 * */
data class DeviceVertUMsg(
    val from: String,
    val task: String,
    val arguments: String
)

/**
 * Vert.x headers for DeviceVerticle use.
 * */
val DeviceVertHeader = mapOf("request" to "ktmeta.dbmgmt.DeviceMgmt")


sealed class DeviceVertArguments

/**
 * Address container for DeviceVertUMsg.
 * @param targetDir Use DeviceAPI.setTargetDir set [targetDir] as root dir.
 * @param deviceType Devices types.
 * This data class is also used for generate uuid according to a [targetDir] and [deviceType]
 * */
data class DevicePathUUID(val targetDir: String, val deviceType: Devices) : DeviceVertArguments() {
    fun toUUID(): String {
        return UUID.nameUUIDFromBytes("$targetDir $deviceType".toByteArray()).toString()
    }
}

/**
 * DeviceAPI methods support. Used by task 'action'
 * @param uuid Use uuid to search device from all available device.
 * @param action Supported actions: setTargetDir, getFullTree, pwd, ls, push, pop.
 * @param path As parameter of some actions: ls, setTargetDir, push.
 * When you use null as [path] for setTargetDir, it will use the path in verticle which set by action getDevice.
 * When you use null as [path] for ls, it will set null as parameter for ls().
 * When you use null as [path] for push, it will fail.
 * */
data class DeviceAction(val uuid: String, val action: String, val path: String?) : DeviceVertArguments()
