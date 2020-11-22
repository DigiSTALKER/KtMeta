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

package references.esclient

import org.apache.http.HttpHost
import org.elasticsearch.action.get.GetRequest
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient

fun main() {
    val client = RestHighLevelClient(
        RestClient.builder(
            HttpHost("localhost", 9200, "http")
        )
    )
    println("Connection established")
    val req = GetRequest(
        "ktmeta_test",
        "ailT1nUBlEKsWf5v0mXr"
    )
    val gr = client.get(req, RequestOptions.DEFAULT)
    val index = gr.index
    val id = gr.id
    if (gr.isExists) {
        val version = gr.version
        val source = gr.sourceAsString
        println("index: $index, id: $id, version: $version")
        println("Doc is $source")
    } else {
        println("document not found")
    }
    client.close()
}