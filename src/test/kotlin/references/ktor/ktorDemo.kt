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

package references.ktor

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*

data class Snippet(val text: String)

// 线程安全
// instead of json
val snippets: MutableList<Snippet> = Collections.synchronizedList(
    mutableListOf(
        Snippet("Hello"),
        Snippet("World")
    )
)

fun Application.module2() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }


    routing {
        get("/snippets") {
            // call.respondText("OK")
            // call.respond(mapOf("OK" to true))
            call.respond(mapOf("Snippets" to synchronized(snippets) { snippets.toList() }))
        }
    }
}

/*
fun main(args: Array<String>) {
    embeddedServer(Netty, 8020, watchPaths = listOf("BlogAppKt"), module = Application::module2).start()
}*/
