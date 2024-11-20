package com.company.kotlinjmixexample.view.expense

import com.company.kotlinjmixexample.entity.Expense
import com.company.kotlinjmixexample.view.main.MainView
import com.vaadin.flow.router.Route
import io.jmix.flowui.view.*

@Route(value = "expenses", layout = MainView::class)
@ViewController("Expense.list")
@ViewDescriptor("expense-list-view.xml")
@LookupComponent("expensesDataGrid")
@DialogMode(width = "64em")
class ExpenseListView : StandardListView<Expense>() {
}