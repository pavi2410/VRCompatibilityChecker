package me.pavi2410.vrcc

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Item(
        @DrawableRes val icon: Int,
        @StringRes val name: Int,
        val result: Boolean
)