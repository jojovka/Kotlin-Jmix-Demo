package com.company.kotlinjmixexample.entity

import io.jmix.core.entity.annotation.JmixGeneratedValue
import io.jmix.core.metamodel.annotation.JmixEntity
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import java.time.LocalDate
import java.util.*

@JmixEntity
@Table(name = "USER_EXPENSE", indexes = [
    Index(name = "IDX_USER_EXPENSE_USER", columnList = "USER_ID"),
    Index(name = "IDX_USER_EXPENSE_EXPENSE", columnList = "EXPENSE_ID")
])
@Entity
open class UserExpense {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    var id: UUID? = null

    @Column(name = "VERSION", nullable = false)
    @Version
    var version: Int? = null

    @JoinColumn(name = "USER_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    var user: User? = null

    @JoinColumn(name = "EXPENSE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    var expense: Expense? = null

    @Column(name = "AMOUNT", nullable = false)
    @NotNull
    var amount: Double? = null

    @PastOrPresent(message = "{msg://com.company.kotlinjmixexample.entity/UserExpense.date.validation.PastOrPresent}")
    @Column(name = "DATE_", nullable = false)
    @NotNull
    var date: LocalDate? = null

    @Column(name = "DETAILS")
    var details: String? = null
}