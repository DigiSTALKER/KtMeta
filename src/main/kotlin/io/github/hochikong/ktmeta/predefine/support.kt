/**
 * @author Hochikong
 * Pre-defined Data
 * */
package io.github.hochikong.ktmeta.predefine


/**
 * Pre-defined supported DBs.
 * @param identity String, used by others module in ktmeta.
 * @param jdbcDriver String, used as JDBC driver name -> Class.forName(driver).
 * @param dataSource String, used by HikariCP as datasource class name.
 * */
enum class SupportedDBs(val identity: String, val jdbcDriver: String, val dataSource: String) {
    SQLite("Sqlite", "org.sqlite.JDBC", "org.sqlite.SQLiteDataSource"),
    PostgreSQL("Postgresql", "org.postgresql.Driver", "org.postgresql.ds.PGSimpleDataSource");
}