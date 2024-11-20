package com.company.kotlinjmixexample.view.user

import com.company.kotlinjmixexample.entity.User
import com.company.kotlinjmixexample.view.main.MainView
import com.vaadin.flow.router.Route
import io.jmix.flowui.view.*

@Route(value = "users", layout = MainView::class)
@ViewController("User.list")
@ViewDescriptor("user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
open class UserListView : StandardListView<User>()
