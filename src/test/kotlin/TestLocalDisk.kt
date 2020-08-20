import io.github.hochikong.ktmeta.device.LocalDisk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import java.io.File

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestLocalDisk {
    private val path = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree"

    @Order(1)
    @Test
    fun testSetPath() {
        val obj = LocalDisk()
        assertEquals(false, obj.setTargetDir("./plugins"))
        assertEquals(false, obj.setTargetDir("$path\\nmsl"))
        assertEquals(true, obj.setTargetDir(path))
    }

    @Order(2)
    @Test
    fun testGetTree() {
        val obj = LocalDisk()
        val x = obj.scanTree(File(path))
        println(x)
        println(x.size)
    }
}