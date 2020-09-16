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

package rest

import io.github.hochikong.ktmeta.rest.ESChecker
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestESChecker {
    @Test
    fun testESChecker() {
        assertEquals(false, ESChecker.isAccessible("http://192.168.2.13:9200"))
        assertEquals(true, ESChecker.isAccessible("http://localhost:9200"))
        val tmp = ESChecker.isAccessibleReport("http://localhost:9200")
        assertEquals(true, tmp!!.isAccessible)
        assertEquals("OK", tmp.status)
        println(tmp.jsonResp)
    }
}