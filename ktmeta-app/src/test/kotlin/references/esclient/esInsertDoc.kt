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
import org.elasticsearch.action.ActionListener
import org.elasticsearch.action.DocWriteResponse
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.common.xcontent.XContentType

fun main() {
    val client = RestHighLevelClient(
        RestClient.builder(
            HttpHost("localhost", 9200, "http")
        )
    )
    println("Connection established")
    val req = IndexRequest("ktmeta_test").apply {
        source(
            """
                {"name": "jackson"}
        """.trimIndent(), XContentType.JSON
        )
    }

    client.indexAsync(req, RequestOptions.DEFAULT, object : ActionListener<IndexResponse> {
        override fun onResponse(response: IndexResponse?) {
            if (response?.result == DocWriteResponse.Result.CREATED) {
                println("Create done")
                client.close()
            }
        }

        override fun onFailure(e: Exception?) {
            println("Create failed")
            client.close()
        }

    })

}