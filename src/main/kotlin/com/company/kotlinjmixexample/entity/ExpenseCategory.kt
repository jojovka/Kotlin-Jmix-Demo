package com.company.kotlinjmixexample.entity

import io.jmix.core.metamodel.datatype.EnumClass

enum class ExpenseCategory(private val id: String) : EnumClass<String> {
    EDUCATION("A"),
    FOOD("B"),
    HEALTH("C"),
    HOUSING("D"),
    TRANSPORTATION("E");

    override fun getId() = id

    companion object {

        @JvmStatic
        fun fromId(id: String): ExpenseCategory? = ExpenseCategory.values().find { it.id == id }
    }
}