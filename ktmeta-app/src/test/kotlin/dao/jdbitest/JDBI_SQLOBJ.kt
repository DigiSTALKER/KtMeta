package dao.jdbitest

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.extension.ExtensionCallback
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.customizer.BindFields
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import java.beans.ConstructorProperties

class JDBI_SQLOBJ {
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

    init {
        jdbi.installPlugins()
    }
}

// Auto converts food_name to foodName, bean mapper relies on getter and setter
/*data class Food(var foodName: String = "", var foodPrice: Int = -1)*/

// constructor mapper relies on constructor parameter, if parameter not match, you should use @ColumnName
// when use constructor mapper, you should use @RegisterConstructorMapper(Food::class) with sql objects
/*class Food() {
    var fn = ""
    var fp = -1

    @ConstructorProperties("food_name", "price")
    constructor(@ColumnName("food_name") nf: String, @ColumnName("price") pf: Int) : this() {
        this.fn = nf
        this.fp = pf
    }

    override fun toString(): String {
        return "Food(fn='$fn', fp=$fp)"
    }
}*/

// although the parameters in constructor not match with properties, but still work...
class Food() {
    var fn = ""
    var fp = -1

    @ConstructorProperties("food_name", "price")
    constructor(fn: String, p: Int) : this() {
        this.fn = fn
        this.fp = p
    }

    override fun toString(): String {
        return "Food(fn='$fn', fp=$fp)"
    }
}

interface FoodMenu {
    @SqlUpdate(
        """
        CREATE TABLE IF NOT EXISTS food_menu (
            id BIGSERIAL PRIMARY KEY ,
            food_name VARCHAR(200) NOT NULL ,
            price INT NOT NULL 
        )
    """
    )
    fun createTable()

    @SqlUpdate("INSERT INTO food_menu(food_name, price) VALUES (:fn, :fp)")
    fun insert(@Bind("fn") foodName: String, @Bind("fp") foodPrice: Int)

//    @SqlUpdate("INSERT INTO food_menu(food_name, price) VALUES (:food.fn, :food.fp)")
//    fun insert(@BindBean("food") food: Food)

//    @SqlUpdate("INSERT INTO food_menu(food_name, price) VALUES (:fn, :fp)")
//    fun insert(@BindBean food: Food)

    @SqlQuery("SELECT food_name, price FROM food_menu;")
    @RegisterConstructorMapper(Food::class)
    fun listFoods(): List<Food>

    @SqlQuery("SELECT 1 FROM food_menu;")
    fun check(): List<Int>
}

fun main() {
    val ins = JDBI_SQLOBJ()
//    val x = ins.jdbi.withExtension(FoodMenu::class.java, ExtensionCallback {
//        it.createTable()
//        it.insert("Shit", 12)
//        it.insert("Peach", 32)
//
//        it.listFoods()
//    })
//
//    println(x)

    val x = ins.jdbi.withExtension(FoodMenu::class.java, ExtensionCallback {
        it.createTable()
//        it.insert(Food("Nosd", 21))
//        it.listFoods()
        it.check()
    })
    println(x)
}