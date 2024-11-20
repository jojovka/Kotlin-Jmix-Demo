package com.company.kotlinjmixexample.view.userexpense

import com.company.kotlinjmixexample.entity.UserExpense
import com.company.kotlinjmixexample.view.main.MainView
import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.HasValueAndElement
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import io.jmix.flowui.kit.action.ActionPerformedEvent
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.model.CollectionContainer
import io.jmix.flowui.model.DataContext
import io.jmix.flowui.model.InstanceContainer
import io.jmix.flowui.model.InstanceLoader
import io.jmix.flowui.view.*
import io.jmix.flowui.view.Target

@Route(value = "userExpenses", layout = MainView::class)
@ViewController("UserExpense.list")
@ViewDescriptor("user-expense-list-view.xml")
@LookupComponent("userExpensesDataGrid")
@DialogMode(width = "64em")
class UserExpenseListView : StandardListView<UserExpense>() {

    @ViewComponent
    private lateinit var dataContext: DataContext

    @ViewComponent
    private lateinit var userExpensesDc: CollectionContainer<UserExpense>

    @ViewComponent
    private lateinit var userExpenseDc: InstanceContainer<UserExpense>

    @ViewComponent
    private lateinit var userExpenseDl: InstanceLoader<UserExpense>

    @ViewComponent
    private lateinit var listLayout: VerticalLayout

    @ViewComponent
    private lateinit var form: FormLayout

    @ViewComponent
    private lateinit var detailActions: HorizontalLayout

    @Subscribe
    fun onInit(event: InitEvent) {
        updateControls(false)
    }

    @Subscribe("userExpensesDataGrid.create")
    fun onUserExpensesDataGridCreate(event: ActionPerformedEvent) {
        dataContext.clear()
        val entity: UserExpense = dataContext.create(UserExpense::class.java)
        userExpenseDc.item = entity
        updateControls(true)
    }

    @Subscribe("userExpensesDataGrid.edit")
    fun onUserExpensesDataGridEdit(event: ActionPerformedEvent) {
        updateControls(true)
    }

    @Subscribe("saveBtn")
    fun onSaveButtonClick(event: ClickEvent<JmixButton>) {
        dataContext.save()
        userExpensesDc.replaceItem(userExpenseDc.item)
        updateControls(false)
    }

    @Subscribe("cancelBtn")
    fun onCancelButtonClick(event: ClickEvent<JmixButton>) {
        dataContext.clear()
        userExpenseDl.load()
        updateControls(false)
    }

    @Subscribe(id = "userExpensesDc", target = Target.DATA_CONTAINER)
    fun onUserExpensesDcItemChange(event: InstanceContainer.ItemChangeEvent<UserExpense>) {
        val entity: UserExpense? = event.item
        dataContext.clear()
        if (entity != null) {
            userExpenseDl.entityId = entity.id
            userExpenseDl.load()
        } else {
            userExpenseDl.entityId = null
            userExpenseDc.setItem(null)
        }
    }

    private fun updateControls(editing: Boolean) {
        form.children.forEach { component ->
            if (component is HasValueAndElement<*, *>) {
                component.isReadOnly = !editing
            }
        }
        detailActions.isVisible = editing
        listLayout.isEnabled = !editing
    }
}