package io.github.hochikong.ktmeta.dao

/**
 * Extends by jdbi xxR interface, which in xxxResourceDAO
 * */
interface ResourcesDAOAPI {
    fun insert(res: DBResourceRecord): Long

    fun update(id: Long, res: ResourcesRecord): Long

    fun query(): List<ResourcesRecord>

    fun delete(id: Long): Long

    fun createTable()

    fun check(): Int?

    fun drop()
}