package com.advaitvedant.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


val LocalBorderPadding = staticCompositionLocalOf { PaddingValues() }
// Define color constants
val GREEN = Color(0xFF63e6be)
val BLUE = Color(0xFF74C0FC)
val RED = Color(0xFFFF3D6E)
val WHITE = Color(0xFFF6FBF3)
val OFF_WHITE = Color(0xFFF0F5ED)
val BLACK = Color(0xFF2D322D)
val OFF_BLACK = Color(0xFF3D403D)

//fun dark(): ColorScheme = darkColorScheme(
//    surface = Color(0xFF101510),
//    background = Color(0xFF181d18),
//    onSurface = Color(0xFFDfe4dc),
//    onSurfaceVariant = Color(0xFFC1c9bf),
//    onBackground = Color(0xFFDfe4dc),
//    primary = ColorCorrect,
//    secondary = ColorInProgress,
//    error = ColorError
//)
//
//
//fun light() = lightColorScheme(
//        surface = Color(0xFFF5F5F5),
//        background = Color(0xFFE0E0E0),
//        onSurface = Color.Black,
//        onBackground = Color.Black,
//        primary = ColorCorrect,
//        secondary = ColorInProgress,
//        error = ColorError
//    )

internal val LocalColorScheme = staticCompositionLocalOf { lightColorScheme() }

//fun lightOpColorScheme(){
//    OpColorScheme(
//        primary = BLUE,
//        secondary = GREEN,
//        surface = Color(0xFFF5F5F5),
//        surfaceVariant = Color(0xFFE0E0E0),
//        background = Color(0xFFE0E0E0),
//        error = RED,
//        onBackground = Color.Black,
//        onBackgroundVariant = Color.Black
//    )
//
//}
@Composable
fun OpTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) darkColorScheme() else lightColorScheme()
    CompositionLocalProvider(
        LocalBorderPadding provides PaddingValues(12.dp),
    ) {
        MaterialTheme(
            colorScheme = colors,
            content = content,
        )
    }
}