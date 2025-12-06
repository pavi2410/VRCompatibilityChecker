package me.pavi2410.vrcc.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class DetailModel(
    val icon: ImageVector,
    val iconColor: Color,
    val name: String,
    val result: Boolean,
    val detail: String? = null
)