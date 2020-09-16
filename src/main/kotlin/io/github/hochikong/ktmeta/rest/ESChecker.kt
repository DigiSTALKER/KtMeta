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

package io.github.hochikong.ktmeta.rest

import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit


/**
 * Simple object to check the ElasticSearch REST service url can be accessed or not.
 * */
object ESChecker {
    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    fun isAccessibleReport(url: String): ESCheckResult? {
        val request = Request.Builder()
            .get()
            .url(url)
            .build()
        try {
            val res = client.newCall(request).execute()
            return ESCheckResult(
                isAccessible = res.message == "OK",
                jsonResp = res.body?.string() ?: "no response",
                status = res.message
            )
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
        return null
    }

    fun isAccessible(url: String): Boolean {
        val res = isAccessibleReport(url)
        return res != null
    }
}

data class ESCheckResult(
    val isAccessible: Boolean,
    val jsonResp: String,
    val status: String
)