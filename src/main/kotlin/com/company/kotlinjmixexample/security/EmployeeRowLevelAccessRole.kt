package com.company.kotlinjmixexample.security

import com.company.kotlinjmixexample.entity.User
import com.company.kotlinjmixexample.entity.UserExpense
import io.jmix.security.role.annotation.JpqlRowLevelPolicy
import io.jmix.security.role.annotation.RowLevelRole

@RowLevelRole(name = "Employee Row Level AccessRole", code = EmployeeRowLevelAccessRole.CODE)
interface EmployeeRowLevelAccessRole {

    companion object {
        const val CODE = "employee-row-level-access-role"
    }

    @JpqlRowLevelPolicy(entityClass = UserExpense::class, where = "{E}.user.id = :current_user_id")
    fun userExpense()
    @JpqlRowLevelPolicy(entityClass = User::class, where = "{E}.id = :current_user_id")
    fun user()
}