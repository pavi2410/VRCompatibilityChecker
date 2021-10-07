package me.pavi2410.vrcc.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DetailModel(
    @DrawableRes val icon: Int,
    val name: String,
    val result: Boolean,
    val detail: String? = null
)