/*
 * Copyright 2020 Hochikong
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.hochikong.ktmeta.service.db_resources

import me.liuwj.ktorm.database.Database
import javax.sql.DataSource

interface DBProviderInterface {
    /**
     * Return Ktorm database with connection pool
     * */
    fun getDatabase(db: String, user: String? = null, password: String? = null): Database?

    /**
     * Return jdbc datasource, provide by HikariCP
     * */
    fun getDataSource(db: String, user: String? = null, password: String? = null): DataSource?

}