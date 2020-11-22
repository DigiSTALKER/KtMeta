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

package service.es

import io.github.hochikong.ktmeta.service.es.ESProvider
import org.elasticsearch.client.RequestOptions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.net.ConnectException

class TestESProvider {
    @Test
    fun testGetSimpleCli() {
        val cli = ESProvider.getDefaultClient("localhost", 9200)
        val rp = cli.info(RequestOptions.DEFAULT)
        assertEquals(rp.version.number, "7.9.0")
    }

    @Test
    fun testFailedConnect() {
        val cli = ESProvider.getDefaultClient("localhost", 9100)
        assertThrows<ConnectException> {
            cli.info(RequestOptions.DEFAULT)
        }
    }
}