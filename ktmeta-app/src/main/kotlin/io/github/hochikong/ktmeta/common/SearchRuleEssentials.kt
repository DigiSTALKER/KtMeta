package io.github.hochikong.ktmeta.common

/**
 * Advance Search match operator.
 *
 * This enum class provides all basic matching operators to unify the entry point of searching on DBMS and ES
 * for advance search.
 *
 * @param code String, a sort string which represents one matching operator.
 * @param desc String, the description of matching operator
 * */
enum class MatchOperators(val code: String, val desc: String) {
    Like("l", "Similar to SQL Like, only used on string columns(attributes)"),
    ILike("il", "Similar to SQL Like but case insensitive"),
    Equal("eq", "Strictly Equals, used on string or numeric columns(attributes)"),
    NEqual("neq", "Not equalsm, used on string or numeric columns(attributes)"),
    GrT("gt", ">, Greater than, only used on numeric columns(attributes)"),
    GrToEt("gte", ">=, Greater than or equal to, only used on numeric columns(attributes)"),
    LeT("lt", "<, Less than, only used on numeric columns(attributes)"),
    LeToEt("lte", "<=, Less than or equal to, only used on numeric columns(attributes)")
}

/**
 * Advance Search rule logical operator.
 *
 * @param code String, a sort string which represents one logical operator.
 * @param desc String, the description of logical operator.
 * */
enum class LogicalOperators(val code: String, val desc: String) {
    AND("and", "And, just like and in sql, has a higher precedence than OR"),
    OR("or", "Or, just like or in sql"),
    NOT("not", "Not"),
    END("end", "Nothing, it means a rule or a compound rule end")
}