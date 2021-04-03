package dao.jdbitest

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.mapper.reflect.FieldMapper
import org.jdbi.v3.core.statement.StatementContext
import java.sql.ResultSet

class JDBI_CORE {
    val url = "jdbc:postgresql://192.168.142.130:5432/testdb"
    private val hikariConfig = HikariConfig().apply {
        jdbcUrl = url
        poolName = "CP-001"
        maximumPoolSize = 9
        username = "dbuser"
        password = "dbuserpass"
    }
    private val ds = HikariDataSource(hikariConfig)

    val jdbi = Jdbi.create(ds)
}

data class TestUser(val username: String, val age: Int)

class TestUserMapper : RowMapper<TestUser> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): TestUser {
        return if (rs != null) {
            TestUser(rs.getString("username"), rs.getInt("age"))
        } else {
            TestUser("", 0)
        }
    }

}


fun main() {
    val jdbiInst = JDBI_CORE()

    // consumer - use
//    print(jdbiInst.jdbi.useHandle<Exception> {
//        it.execute("INSERT INTO users (username, age) VALUES ( 'KL' ,23)")
//    })

    // callback - with
    val names = jdbiInst.jdbi.withHandle<List<String>, Exception> {
        it.createQuery("SELECT * FROM users;").mapTo(String::class.java).list()
    }
    println(names)

    val name = jdbiInst.jdbi.withHandle<String, Exception> {
        it
            .createQuery("SELECT username FROM users WHERE id = ?")
            .bind(0, 2)
            .mapTo(String::class.java)
            .one()
    }
    print("Id 2, username is $name")

    val listMap = jdbiInst.jdbi.withHandle<List<Map<String, Any>>, Exception> {
        it.createQuery("SELECT * FROM users;").mapToMap().list()
    }
    println(listMap)

    println("${listMap[0]["id"]}, isinstance of ${listMap[0]["id"]!!::class.simpleName}")
    println("${listMap[0]["username"]}, isinstance of ${listMap[0]["username"]!!::class.simpleName}")
    println(listMap[0]["age"])

    // row mapper
    val userObjects = jdbiInst.jdbi.withHandle<List<TestUser>, Exception> {
        it.createQuery("SELECT username, age FROM users;").map { rs, _ ->
            TestUser(
                rs.getString("username"),
                rs.getInt("age")
            )
        }.list()
    }

    println("User objects:")
    userObjects.forEach { println(it) }
    println("-----")

    // user implement row mapper
    val userObjects_ = jdbiInst.jdbi.withHandle<List<TestUser>, Exception> {
        it.createQuery("SELECT username, age FROM users WHERE id > 2;").map(TestUserMapper()).list()
    }
    userObjects_.forEach { println(it) }

    // column mapper
    val usernames = jdbiInst.jdbi.withHandle<List<String>, Exception> {
        it.select("SELECT username FROM users;").map { rs, col, _ ->
            rs.getString(col)
        }.list()
    }
    println("All usernames: $usernames")
}