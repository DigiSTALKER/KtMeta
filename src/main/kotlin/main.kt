import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.kotlin.core.deploymentOptionsOf
import io.vertx.kotlin.core.eventbus.deliveryOptionsOf
import java.util.*
import kotlin.system.exitProcess


/**
 * Test only
 * */
fun main() {
    val mapper = ObjectMapper().registerModule(KotlinModule())
    print("Enter your name: ")
    val input = Scanner(System.`in`).nextLine()
    if (input.trim() == "password") {
        println("You can access the db.")
    }

    val ids = mutableListOf<String>()
    val vertx = Vertx.vertx()
    println("size is:  ${VertxOptions().eventLoopPoolSize}")

    vertx.deployVerticle(ConsumePrint::class.java.name) { res ->
        run {
            if (res.succeeded()) {
                println("Deployment id is: ${res.result()}")
                ids.add(res.result())
            } else {
                println("Deployment failed!")
            }
        }
    }

    vertx.deployVerticle("Waste", deploymentOptionsOf(worker = true)) { res ->
        run {
            if (res.succeeded()) {
                println("Deployment id is: ${res.result()}")
                ids.add(res.result())
            } else {
                println("Deployment failed!")
            }
        }
    }

    vertx.deployVerticle(WorkerV::class.java.name, deploymentOptionsOf(worker = true)) { res ->
        run {
            if (res.succeeded()) {
                println("Deployment id is: ${res.result()}")
                ids.add(res.result())
            } else {
                println("Deployment failed!")
            }
        }
    }

//    val restServer = RestMain()
//    val loopServer = Coop()
//    Thread(restServer).apply { start() }
//    Thread(loopServer).apply { start() }

//    val path = "plugins/ktmeta-driver-1.0-SNAPSHOT.jar"
//    val absp = "file:C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\plugins\\ktmeta-driver-1.0-SNAPSHOT.jar"
//    val m = JarFile(path).manifest
//    val ma = m.mainAttributes
//    val classNames = ma.keys.filter { it.toString().startsWith("Driver-Name") }.map { ma[it] }.toList()
//
//    println(classNames)

//    val urlClassLoader = URLClassLoader(arrayOf(URL(absp)))
//    val driver = urlClassLoader.loadClass(className)
//    val instance = driver.newInstance()
//    println(instance is AbstractDriver)
//    val met: Method = driver.getMethod("accessDrive")
//    println(met.invoke(instance))
//    urlClassLoader.close()

    val keyForTest1 = listOf("waste", "no waste")

    println("Enter to exit...")
    val read = object : Runnable {
        @Volatile
        var exit = false
        override fun run() {
            while (!exit) {
                vertx.eventBus().consumer<String>("url3") { res ->
                    println("Main received from url3: ${res.body()}")
                }
            }
        }

        fun close() {
            exit = true
        }
    }
    Thread(read).start()
    while (true) {
        val inputA = Scanner(System.`in`).nextLine().trim()
        if (inputA.isBlank()) {
            println("You are exiting!!!!!")
            /*if (ids.isNotEmpty()) {
                for (id in ids) {
                    vertx.undeploy(id) { res ->
                        run {
                            if (res.succeeded()) {
                                println("Undeployed done")
                            } else {
                                println("Undeployed failed!")
                            }
                        }
                    }
                }
            }*/
            vertx.close()
            read.close()
            exitProcess(0)
        } else if (keyForTest1.contains(inputA)) {
            when (inputA) {
                "waste" -> {
                    vertx.eventBus().request<String>(
                        "url2",
                        mapper.writeValueAsString(Person("cxk", 24)),
                        deliveryOptionsOf(headers = mapOf("msg" to "waste"))
                    ) { reply ->
                        if (reply.succeeded()) {
                            println("Main received reply ${reply.result().body()}")
                        } else {
                            println("Main received failed reply ${reply.result().body()}")
                        }
                    }
                }
                else -> {
                    vertx.eventBus().request<String>(
                        "url2",
                        mapper.writeValueAsString(Person("css", 24)),
                        deliveryOptionsOf(headers = mapOf("msg" to "no waste"))
                    ) { reply ->
                        if (reply.succeeded()) {
                            println("Main received reply: ${reply.result().body()}")
                        } else {
                            println("Main received failed reply: ${reply.cause().message}")
                        }
                    }
                }
            }
        } else if (inputA.contains("sleep")) {
            vertx.eventBus().send("worker", inputA)
        } else {
            vertx.eventBus().send("url1", inputA)
        }
    }
}

data class Person(val name: String, val age: Int)