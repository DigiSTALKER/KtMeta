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

package io.github.hochikong.ktmeta.service.device

import io.github.hochikong.ktmeta.common.InitialDeviceFailed
import io.github.hochikong.ktmeta.common.SupportedDevices
import io.github.hochikong.ktmeta.service.device.impl.LocalDiskDriver


object DeviceProvider {
    /**
     * Return a service.device instance by Devices type.
     * */
    fun getDevice(type: SupportedDevices): DeviceAPI {
        return getDeviceByType(type.className)
    }

    /**
     * Return a service.device instance by name
     * */
    private fun getDeviceByType(name: String): DeviceAPI {
        val deviceClass = Class.forName(name)
        val inst = deviceClass.newInstance()
        if (inst is DeviceAPI) return inst
        throw InitialDeviceFailed("DeviceProvider.getDevice failed!")
    }

    /**
     * Return a service.device instance by Devices.identity
     */
    fun getDevice(identity: String): DeviceAPI {
        val officialSupportIdentity = SupportedDevices.values().map { it.identity }.toList()
        if (officialSupportIdentity.contains(identity)) {
            when (identity) {
                "LocalDiskDriver" -> return LocalDiskDriver()
            }
        }
        throw InitialDeviceFailed("DeviceProvider.getDevice failed, identity '$identity' not supported.")
    }
}
