package io.lzaycoe.zynema.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette =
    darkColorScheme(primary = Purple200, primaryContainer = Purple700, secondary = Teal200)

private val LightColorPalette =
    lightColorScheme(
        primary = Purple500,
        primaryContainer = Purple200,
        secondary = Pink500,
        secondaryContainer = Pink200,
        background = White,
        surface = Grey200,
        onPrimary = White,
        onSecondary = Black,
        onBackground = Black,
        onSurface = Black,
        error = Red200,
        onError = White
    )

@Composable
fun ZynemaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors =
        if (darkTheme) {
            DarkColorPalette
        } else {
            LightColorPalette
        }

    MaterialTheme(colorScheme = colors, typography = Typography, shapes = Shapes, content = content)
}
