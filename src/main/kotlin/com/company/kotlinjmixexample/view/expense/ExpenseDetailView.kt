package com.company.kotlinjmixexample.view.expense

import com.company.kotlinjmixexample.entity.Expense
import com.company.kotlinjmixexample.view.main.MainView
import com.vaadin.flow.router.Route
import com.company.kotlinjmixexample.extension.configureAsPrimary
import com.company.kotlinjmixexample.sealedclass.OperationStatus
import com.vaadin.flow.component.UI
import io.jmix.flowui.Notifications
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.view.*
import kotlinx.coroutines.*
import org.springframework.beans.factory.annotation.Autowired

@Route(value = "expenses/:id", layout = MainView::class)
@ViewController("Expense.detail")
@ViewDescriptor("expense-detail-view.xml")
@EditedEntityContainer("expenseDc")
class ExpenseDetailView : StandardDetailView<Expense>(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext = Dispatchers.Default + job

    @Autowired
    private lateinit var notifications: Notifications

    @ViewComponent
    private lateinit var checkBtn: JmixButton


    @Subscribe
    fun onInit(event: InitEvent) {
        checkBtn.configureAsPrimary("Sealed класс отработал"){
            val ui = UI.getCurrent()
            launch {
                try {
                    ui.access {
                        notifications.create("Операция выполняется...")
                                .withType(Notifications.Type.WARNING)
                                .show()
                    }
                    val status = withContext(Dispatchers.IO) {
                        performLongRunningOperation()
                    }
                    ui.access {
                        when (status) {
                            is OperationStatus.Success -> {
                                notifications.create("Успех: ${status.message}")
                                        .withType(Notifications.Type.SUCCESS)
                                        .show()
                            }
                            is OperationStatus.Failure -> {
                                notifications.create("Ошибка: ${status.error.message}")
                                        .withType(Notifications.Type.ERROR)
                                        .show()
                            }
                            OperationStatus.Loading -> {
                            }
                        }
                    }
                } catch (e: Exception) {
                    ui.access {
                        notifications.create("Ошибка корутины: ${e.message}")
                                .withType(Notifications.Type.ERROR)
                                .show()
                    }
                }
            }
        }
    }

    private suspend fun performLongRunningOperation(): OperationStatus  {
        return try {
            delay(2000)
            OperationStatus.Success("Данные успешно обработаны")
        } catch (e: Exception) {
            OperationStatus.Failure(e)
        }
    }

    @Subscribe
    fun onBeforeClose(event: BeforeCloseEvent) {
        coroutineContext.cancel()
    }
}