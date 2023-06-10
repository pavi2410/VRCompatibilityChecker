package me.pavi2410.vrcc.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Colors.Blue500,
    primaryVariant = Colors.Blue400,
    secondary = Colors.Blue900,
    onSecondary = Colors.Blue400,
    background = Color.Black
)

private val LightColorPalette = lightColors(
    primary = Colors.Blue500,
    primaryVariant = Colors.Blue700,
    secondary = Colors.Blue50,
    onSecondary = Colors.Blue700
)

@Composable
fun VrccTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes
    ) {
        val systemUiController = rememberSystemUiController()

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = !darkTheme
            )
        }

        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}