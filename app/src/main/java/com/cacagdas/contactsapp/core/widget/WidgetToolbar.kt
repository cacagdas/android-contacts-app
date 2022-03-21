package com.cacagdas.contactsapp.core.widget

import androidx.annotation.DrawableRes

class WidgetToolbar(
    val title: String? = null,
    val menu: List<ToolbarMenu>? = null,
)

class ToolbarMenu(
    val title: String? = null,
    @DrawableRes val icon: Int? = null,
    val action: (() -> Unit)? = null
)
