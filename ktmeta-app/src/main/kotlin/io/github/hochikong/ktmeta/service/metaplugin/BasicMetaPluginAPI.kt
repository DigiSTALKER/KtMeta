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

package io.github.hochikong.ktmeta.service.metaplugin

import io.github.hochikong.ktmeta.service.device.DeviceAPI

/**
 * All metadata plugins should implement this basic interface.
 * This interface only supports the use of RDBMS to store metadata.
 *
 * Important: A single metadata document can only be insert or marked 'deleted'.
 * If you want to update a metadata document itself,
 * you can only mark it 'deleted' and insert new one to override that one.
 *
 * Important: Although Abstract, Custom Tags and Preview belong to the metadata document,
 * they can be modified in-place, unlike the document itself.
 *
 * Supported features:
 * 1. Search result paging
 * 2. Edit abstract
 * 3. Edit custom tags
 * 4. Edit preview
 * 5. Import/Export metadata document (as json). Mark delete. Insert new document to cover the deleted one.
 * 6. Full-Text search support and search rules
 * 7. Advance search support and search rules
 * 8. Aggregate search support
 * 9. Add custom jMenuItem in 'Tools' and new jDialog or jFrame
 * 10. Plugin description
 * */
interface BasicMetaPluginAPI {
    /**
     * Return a html text to describe the plugin
     * */
    fun getHTMLDescription(): String

    /**
     * Return a sql for getting the page from [pageLocation] with [pageSize] rows.
     * */
    fun getPagingConfig(pageSize: Int, pageLocation: Int): String

    /**
     * Receive new [content] to update [id]: Metadata's Abstract then return the latest abstract.
     *
     * If update failed, return the last abstract content.
     * If content is null, return the last abstract content.
     * */
    fun putAbstract(id: Int, content: String?): String

    /**
     * Similar to putAbstract().
     *
     * If [override] is true, new [tags] will cover the old one.
     * If [override] is false, new [tags] will append to the old one.
     * */
    fun putCustomTags(id: Int, tags: List<String>?, override: Boolean): List<String>

    /**
     * Similar to putAbstract()
     * Preview can be a text or a base64 code convert from a photo.
     *
     * If [override] is true, new [previews] will cover the old one.
     * If [override] is false, new [previews] will append to the old one.
     * */
    fun putPreview(id: Int, previews: List<String>?, override: Boolean): List<String>

    /**
     * Official import metadata support.
     * Import one or more metadata documents into metadata library.
     * If a metadata library contains any data marked 'deleted', new metadata documents will use those
     * 'deleted' row first.
     *
     * @return A list which contains all metadata documents' ids created by method.
     * */
    fun postMetadata(device: DeviceAPI): List<Int>

    /**
     * Mark all rows which their id in [ids] 'deleted'.
     *
     * @return How many rows affected.
     * */
    fun deleteMetadata(ids: List<Int>): Int

    /**
     * Export metadata as a file. If done return true, or false.
     * */
    fun exportMetadata(id: Int): Boolean

    /**
     * Return the helper message(e.g., Search rules) of full-text search.
     * */
    fun fullTextSearchHelper(): String

    /**
     *
     * */
    fun fullTextSearch()

}