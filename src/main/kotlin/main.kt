import io.github.hochikong.ktmeta.driver.AbstractDriver
import java.lang.reflect.Method
import java.net.URL
import java.net.URLClassLoader
import java.nio.file.Files
import java.util.*
import java.util.jar.JarFile
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

    val path = "plugins/ktmeta-driver-1.0-SNAPSHOT.jar"
    val absp = "file:C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\plugins\\ktmeta-driver-1.0-SNAPSHOT.jar"
    val m = JarFile(path).manifest
    val ma = m.mainAttributes
    val classNames = ma.keys.filter { it.toString().startsWith("Driver-Name") }.map { ma[it] }.toList()

    println(classNames)

//    val urlClassLoader = URLClassLoader(arrayOf(URL(absp)))
//    val driver = urlClassLoader.loadClass(className)
//    val instance = driver.newInstance()
//    println(instance is AbstractDriver)
//    val met: Method = driver.getMethod("accessDrive")
//    println(met.invoke(instance))
//    urlClassLoader.close()


    print("Enter to exit...")
    if (Scanner(System.`in`).nextLine() is String) {
        exitProcess(0)
    }
}