import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import javax.sql.DataSource;

public class JDBIOBJ {
    public static void main(String[] args) {
        JDBICFG cfg = new JDBICFG();
        cfg.jdbi.useExtension(Drinks.class, dao -> {
            dao.createTable();

            dao.insert("coffie");
            dao.insert("tea");
        });


    }
}

class JDBICFG {
    String url = "jdbc:postgresql://192.168.142.130:5432/testdb";
    HikariConfig cfg = new HikariConfig();
    DataSource ds;
    Jdbi jdbi;

    public JDBICFG() {
        cfg.setJdbcUrl(url);
        cfg.setUsername("dbuser");
        cfg.setPassword("dbuserpass");

        ds = new HikariDataSource(this.cfg);
        jdbi = Jdbi.create(ds);
        jdbi.installPlugin(new SqlObjectPlugin());
    }
}

interface Drinks {
    @SqlUpdate("CREATE TABLE drinks (id BIGSERIAL PRIMARY KEY , name VARCHAR(200))")
    void createTable();

    @SqlUpdate("INSERT INTO drinks(name) VALUES (:dn)")
    void insert(@Bind("dn") String drinkName);
}