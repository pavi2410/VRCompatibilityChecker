package me.pavi2410.vrcc.ui

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.addPathNodes

val checkIcon = materialIcon("check") {
    val pathData = "M9,16.17L4.83,12l-1.42,1.41L9,19 21,7l-1.41,-1.41z"

    materialPath {
        addPath(fill = SolidColor(greenA400), pathData = addPathNodes(pathData))
    }
}

val crossIcon = materialIcon("cross") {
    val pathData = "M19,6.41L17.59,5 12,10.59 6.41,5 5,6.41 10.59,12 5,17.59 6.41,19 12,13.41 17.59,19 19,17.59 13.41,12z"

    materialPath {
        addPath(fill = SolidColor(red500), pathData = addPathNodes(pathData))
    }
}