/*
 * Copyright 2020 Hochikong
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.github.hochikong.ktmeta.dbmgmt

import io.github.hochikong.ktmeta.predefined.SupportedDBs

/**
 * Actor listen address
 * */
const val DBVertListenAddr = "ktmeta.dbmgmt.DBMgmt"

/**
 * DBVerticle message
 * @param from Who send this message
 * @param task Special string for different tasks: addDatabase, removeDatabase, queryReg, checkCatalog, grantDB.
 * @param arguments DBArguments for the title: a json string from jackson subclass of DBArguments.
 * */
data class DBVertUMsg(
    val from: String,
    val task: String,
    val arguments: String
)

/**
 * Vert.x headers for DBVerticle use.
 * */
val DBMgmtHeader = mapOf("request" to "ktmeta.dbmgmt.DBMgmt")

// -----------------------------------------------------------------------
sealed class DBArguments

/**
 * Vert.x msg arguments data class for DBMgmt.addDatabase
 * */
data class AddDatabase(
    val type: SupportedDBs,
    val name: String,
    val desc: String,
    val user: String,
    val password: String,
    val url: String
) : DBArguments()


/**
 * Vert.x arguments data class for DBMgmt.removeDatabase
 * @param
 * */
data class RemoveDatabase(val name: String) : DBArguments()

/**
 * Vert.x arguments data class for DBMgmt.getXXX
 * @param type Current supported type are: "JDBC", "KtormDB", "KtormPool"
 * */
data class GrantDB(val name: String, val type: String) : DBArguments()

/**
 * Vert.x arguments data class for DBMgmt.queryReg
 * */
data class QueryReg(val task: String = "QueryReg") : DBArguments()

/**
 * Vert.x msg title for DBMgmt.checkCatalog
 * */
data class CheckCatalog(val task: String = "CheckCatalog") : DBArguments()