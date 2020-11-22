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
import org.elasticsearch.action.DocWriteRequest
import org.elasticsearch.action.DocWriteResponse
import org.elasticsearch.action.bulk.BulkRequest
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
    val bk = BulkRequest().apply {
        // {"name": "cxk"}
        add(IndexRequest("posts_test").id("1").source(XContentType.JSON, "name", "cxk"))
        // {"name": "sxc"}
        add(IndexRequest("posts_test").id("2").source(XContentType.JSON, "name", "sxc"))
        add(IndexRequest("posts_test").id("3").source(XContentType.JSON, "name", "nmsl"))
    }
    val br = client.bulk(bk, RequestOptions.DEFAULT)
    for (rp in br) {
        val resp = rp.getResponse<DocWriteResponse>()
        when (rp.opType) {
            DocWriteRequest.OpType.INDEX -> println((resp as IndexResponse).result)
            else -> print("Nothing")
        }
    }
    client.close()
}