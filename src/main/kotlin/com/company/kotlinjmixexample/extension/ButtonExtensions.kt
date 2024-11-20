package com.company.kotlinjmixexample.extension

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.notification.Notification

fun Button.configureAsPrimary(text: String, onClick: () -> Unit) {
    this.text = text
    this.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
    this.addClickListener {
        onClick()
    }
}