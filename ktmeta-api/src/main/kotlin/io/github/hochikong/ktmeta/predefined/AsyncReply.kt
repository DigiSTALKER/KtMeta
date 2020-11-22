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

package io.github.hochikong.ktmeta.predefined

sealed class VertAsyncReply

/**
 * Result class for reply to others verticle who perform a action before.
 * @param taskUUID When a verticle call a async task, it will receive a taskUUID and wait for message from DeviceVerticle.
 * @param from Who send this message.
 * @param loads Any data, should convert it to a String before sending.
 * @param decoder Use what data class to parse loads.
 * */
data class AsyncResultPacket(
    val taskUUID: String,
    val from: String,
    val loads: String,
    val decoder: String,
) : VertAsyncReply()

/**
 * Use this data class to store string in list.
 * */
data class VertListLoads(val data: List<String>) : VertAsyncReply()

/**
 * Use this data class to store string in map.
 * */
data class VertMapLoads(val data: Map<String, String>) : VertAsyncReply()