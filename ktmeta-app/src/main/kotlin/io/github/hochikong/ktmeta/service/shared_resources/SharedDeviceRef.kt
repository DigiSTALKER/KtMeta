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

package io.github.hochikong.ktmeta.service.shared_resources

import io.github.hochikong.ktmeta.service.device.DeviceAPI
import java.util.concurrent.ConcurrentHashMap

/**
 * Shared service.device references singleton.
 * Used by verticle to keep references of devices.
 * */
object SharedDeviceRef {
    private val devRefs = ConcurrentHashMap<String, DeviceAPI>()

    fun addRef(key: String, dev: DeviceAPI) {
        devRefs[key] = dev
    }

    fun getRef(key: String): DeviceAPI? {
        return devRefs[key]
    }

    fun removeRef(key: String) {
        devRefs.remove(key)
    }
}