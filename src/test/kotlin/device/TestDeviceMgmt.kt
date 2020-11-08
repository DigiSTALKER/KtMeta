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

package device

import io.github.hochikong.ktmeta.device_resources.DeviceMgmt
import io.github.hochikong.ktmeta.predefined.InitialDeviceFailed
import io.github.hochikong.ktmeta.predefined.SupportedDevices
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TestDeviceMgmt {
    @Test
    fun testGetDevice() {
        val obj = DeviceMgmt.getDevice(SupportedDevices.LocalDevice)
        assertEquals(true, obj.setTargetDir("C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree"))
        val tree = obj.getFullTree()
        tree.forEach { println(it) }
    }

    @Test
    fun testGetDeviceByIdentity() {
        assertThrows<InitialDeviceFailed> { DeviceMgmt.getDevice("ssdad") }
        val obj = DeviceMgmt.getDevice(SupportedDevices.LocalDevice.identity)
        assertEquals(true, obj.setTargetDir("C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree"))
        println("current is ${obj.pwd()}")
    }
}