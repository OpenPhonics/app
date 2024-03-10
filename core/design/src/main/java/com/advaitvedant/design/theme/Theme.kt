package com.advaitvedant.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp


val LocalBorderPadding = staticCompositionLocalOf { PaddingValues() }

@Composable
fun OpTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) darkColorScheme() else lightColorScheme()
    CompositionLocalProvider(
        LocalBorderPadding provides PaddingValues(12.dp)
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = typography,
            content = content,
        )
    }
}