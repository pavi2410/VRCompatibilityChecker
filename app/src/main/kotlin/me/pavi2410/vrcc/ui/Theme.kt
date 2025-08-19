package me.pavi2410.vrcc.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Colors.Blue500,
    secondary = Colors.Blue900,
    onSecondary = Colors.Blue400,
    background = Color.Black
)

private val LightColorPalette = lightColorScheme(
    primary = Colors.Blue500,
    secondary = Colors.Blue50,
    onSecondary = Colors.Blue700
)

@Composable
fun VrccTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
    ) {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}