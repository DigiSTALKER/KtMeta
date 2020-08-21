import io.github.hochikong.ktmeta.device.LocalDisk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import io.github.hochikong.ktmeta.device.FileRow
import io.github.hochikong.ktmeta.predefined.FileType

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestLocalDisk {
    private val path = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree"
    private val data = listOf(
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = false,
            fatherPath = ".",
            hasChild = true,
            type = FileType.Directory
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub1",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasChild = true,
            type = FileType.Directory
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub1\\sub11",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub1",
            hasChild = true,
            type = FileType.Directory
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub1\\sub11\\sub1a.txt",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub1\\sub11",
            hasChild = false,
            type = FileType.File
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasChild = true,
            type = FileType.Directory
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub22",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2",
            hasChild = true,
            type = FileType.Directory
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub22\\sub222",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub22",
            hasChild = true,
            type = FileType.Directory
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub22\\sub222\\nmsl.txt",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub22\\sub222",
            hasChild = false,
            type = FileType.File
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2\\sub2a.txt",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\sub2",
            hasChild = false,
            type = FileType.File
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\suba.txt",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasChild = false,
            type = FileType.File
        ),
        FileRow(
            selfPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree\\subb.txt",
            rootPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasFather = true,
            fatherPath = "C:\\Users\\ckhoi\\IdeaProjects\\ktmeta\\src\\test\\resources\\tree",
            hasChild = false,
            type = FileType.File
        )
    )


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
        assertEquals(true, obj.setTargetDir(path))
        val rows = obj.getFullTree()
        // File("logs.txt").writeText(rows.map { "$it \n" }.reduce { acc, s -> acc + s })
        assertEquals(data.size, rows.size)
        for (index in data.indices) {
            assertEquals(data[index], rows[index])
        }
        println(obj.absPath)
        obj.dirs.forEach { println(it) }
    }

    @Order(3)
    @Test
    fun testCdPwd() {
        val x = LocalDisk(path)
        println(x.push("sub222"))
        println(x.dirs)
        println(x.checkStack())
        println(x.pwd())
    }
}