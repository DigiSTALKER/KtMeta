package io.github.hochikong.ktmeta.service.metaplugin

/**
 * A search context created by the searching interface keeps search rules and paging configs,
 * it survives until you close the searching results tab.
 *
 * The search context only keeps configurations, not for search results.
 *
 * */
class SearchContext(pageLocation: String) {
    // keep the rules
    private val rulesStorage = linkedMapOf<Int, SearchRules>()
    private var ruleAppendCounter = 0

    // paging configurations, a page location can be a string or a string that can be parsed as a number.
    private var currentPageLocation: String = pageLocation
    private var currentPageSize = 0

    // It determines what attributes will show on the search result table.
    private var displayAttributes = listOf<String>()

    constructor(pageLocation: String, pageSize: Int) : this(pageLocation) {
        this.currentPageSize = pageSize
    }
}