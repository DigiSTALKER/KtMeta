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

import io.github.hochikong.ktmeta.common.HowToMatch
import io.github.hochikong.ktmeta.common.RuleConnector

sealed class AdvanceSearchRules

/**
 * A basic advance search rule.
 *
 * Examples:
 * SELECT * FROM manga WHERE author = 'X1'
 * TO Basic Rule -> BasicRule('author', Equals, 'X1', END)
 *
 * SELECT * FROM manga WHERE title LIKE '%gal%'
 * TO BasicRule -> BasicRule('title', Like, 'gal')
 *
 * @param attribute String, a metadata library's attribute or column name.
 * @param howToMatch HowToMatch, a enum class member to describe how to match the [userInput].
 * @param userInput String, multiple value split by spaces will be treated like a single phrase.
 * @param ruleConnectorToNext RuleConnector, a enum class member to describe how to connect
 * to the next rule. If you want to end the rules, just use END class.
 * */
data class BasicRule(
    val attribute: String,
    val howToMatch: HowToMatch,
    val userInput: String,
    val ruleConnectorToNext: RuleConnector
) : AdvanceSearchRules()

/**
 * A dataclass for compound rule.
 *
 * a compound rule just like a sub condition contains 1 or more conditions
 * in SQL which wrapped in parentheses.
 *
 * Examples:
 * SELECT * FROM manga WHERE author = 'X1' OR (author = 'X1' and title LIKE '%gals%')
 * Condition in parentheses to CompoundRule ->
 *     CompoundRule(
 *         {
 *             1=BasicRule('author', Equals, 'X1', AND),
 *             2=BasicRule('title', Like, 'gals', END)
 *         },
 *         END
 *     )
 *
 * @param rules LinkedHashMap, the key is the rule order which starts from 0, the value is a BasicRule.
 * @param ruleConnectorToNext RuleConnector, a enum class member to
 * describe how to connect to the next BasicRule or CompoundRule.
 * */
data class CompoundRule(
    val rules: LinkedHashMap<Int, BasicRule>,
    val ruleConnectorToNext: RuleConnector
): AdvanceSearchRules()