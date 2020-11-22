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

package io.github.hochikong.ktmeta.service.es

import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.slf4j.LoggerFactory

object ESProvider {
    private val logger = LoggerFactory.getLogger("ktmeta-service-esprovider")

    /**
     * Provide the simplest ElasticSearch high level client.
     * */
    fun getDefaultClient(host: String, port: Int): RestHighLevelClient {
        val cli = RestHighLevelClient(
            RestClient.builder(
                HttpHost(host, port, "http")
            )
        )
        logger.info("Connection for $host@$port established.")
        return cli
    }
}