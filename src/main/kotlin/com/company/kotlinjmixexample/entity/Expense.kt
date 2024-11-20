package com.company.kotlinjmixexample.entity

import io.jmix.core.entity.annotation.JmixGeneratedValue
import io.jmix.core.metamodel.annotation.InstanceName
import io.jmix.core.metamodel.annotation.JmixEntity
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.util.*

@JmixEntity
@Table(name = "EXPENSE", uniqueConstraints = [
    UniqueConstraint(name = "IDX_EXPENSE_UNQ", columnNames = ["NAME"])
])
@Entity
open class Expense {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    var id: UUID? = null

    @Column(name = "VERSION", nullable = false)
    @Version
    var version: Int? = null

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    var name: String? = null

    @Column(name = "CATEGORY", nullable = false)
    @NotNull
    private var category: String? = null

    fun getCategory(): ExpenseCategory? = category?.let { ExpenseCategory.fromId(it) }

    fun setCategory(category: ExpenseCategory?) {
        this.category = category?.id
    }
}