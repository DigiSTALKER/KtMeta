import io.vertx.core.Vertx
import io.vertx.kotlin.core.deploymentOptionsOf
import java.util.*
import kotlin.system.exitProcess


/**
 * Test only
 * */
fun main() {
    print("Enter your name: ")
    val input = Scanner(System.`in`).nextLine()
    if (input.trim() == "password") {
        println("You can access the db.")
    }

    val ids = mutableListOf<String>()
    val vertx = Vertx.vertx()
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

    vertx.deployVerticle(Waste::class.java.name, deploymentOptionsOf(worker = true)) { res ->
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


    print("Enter to exit...")
    while (true) {
        vertx.eventBus().consumer<String>("url2") { msg ->
            println("From server test1: ${msg.body()}")
        }
        vertx.eventBus().consumer<String>("url1") { msg ->
            println("From server test2: ${msg.body()}")
        }

        val inputA = Scanner(System.`in`).nextLine().trim()
        if (inputA.isBlank()) {
            println("You are exiting!!!!!")
            if (ids.isNotEmpty()) {
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
            }
            vertx.close()
            exitProcess(0)
        } else if (inputA == "waste") {
            vertx.eventBus().send("url2", inputA)
        } else {
            vertx.eventBus().publish("url1", inputA)
        }
    }
}