package com.company.kotlinjmixexample.security

import com.company.kotlinjmixexample.entity.Expense
import com.company.kotlinjmixexample.entity.User
import com.company.kotlinjmixexample.entity.UserExpense
import io.jmix.security.model.EntityAttributePolicyAction
import io.jmix.security.model.EntityPolicyAction
import io.jmix.security.role.annotation.EntityAttributePolicy
import io.jmix.security.role.annotation.EntityAttributePolicyContainer
import io.jmix.security.role.annotation.EntityPolicy
import io.jmix.security.role.annotation.ResourceRole
import io.jmix.securityflowui.role.annotation.MenuPolicy
import io.jmix.securityflowui.role.annotation.ViewPolicy

@ResourceRole(name = "Employee Role", code = EmployeeRole.CODE, scope = ["UI"])
interface EmployeeRole {

    companion object {
        const val CODE = "employee-role"
    }

    @MenuPolicy(menuIds = ["UserExpense.list"])
    @ViewPolicy(viewIds = ["Expense.list", "User.list", "UserExpense.list"])
    fun screens()

    @EntityAttributePolicyContainer(
            EntityAttributePolicy(entityClass = Expense::class, attributes = ["*"], action = EntityAttributePolicyAction.VIEW))
    @EntityPolicy(entityClass = Expense::class, actions = [EntityPolicyAction.READ])
    fun expense()

    @EntityAttributePolicyContainer(
            EntityAttributePolicy(entityClass = User::class, attributes = ["*"], action = EntityAttributePolicyAction.VIEW))
    @EntityPolicy(entityClass = User::class, actions = [EntityPolicyAction.READ, EntityPolicyAction.UPDATE])
    fun user()

    @EntityAttributePolicyContainer(
            EntityAttributePolicy(entityClass = UserExpense::class, attributes = ["*"], action = EntityAttributePolicyAction.VIEW))
    @EntityPolicy(entityClass = UserExpense::class, actions = [EntityPolicyAction.CREATE, EntityPolicyAction.READ, EntityPolicyAction.UPDATE])
    fun userExpense()
}