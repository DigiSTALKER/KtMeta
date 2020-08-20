package io.github.hochikong.ktmeta.dbmgmt

/**
 * Generate catalog by the return of Maintainer.queryAllRows
 * */
object DBRegCatalog {
    private var catalog: MutableMap<String, RegRow> = mutableMapOf()

    fun updateCatalog(queryResult: List<List<Any>>?): Boolean {
        // refresh
        if (catalog.isNotEmpty()) {
            catalog = mutableMapOf()
        }

        if (queryResult != null) {
            for (row in queryResult) {
                val tmp = row.regOut()
                catalog[tmp.name] = tmp
            }
            return true
        }
        return false
    }

    fun keys(): MutableSet<String> {
        return catalog.keys
    }

    operator fun get(key: String): RegRow? {
        if (key !in catalog.keys) return null
        return catalog[key]
    }

}