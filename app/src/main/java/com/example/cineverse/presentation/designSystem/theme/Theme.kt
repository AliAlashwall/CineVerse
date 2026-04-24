package com.example.cineverse.presentation.designSystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import com.example.cineverse.presentation.designSystem.colors.CineVerseColors
import com.example.cineverse.presentation.designSystem.colors.LocalCineVerseColors
import com.example.cineverse.presentation.designSystem.colors.darkThemeColors
import com.example.cineverse.presentation.designSystem.colors.lightThemeColors
import com.example.cineverse.presentation.designSystem.typography.CineVerseTextStyle
import com.example.cineverse.presentation.designSystem.typography.DefaultTextStyle
import com.example.cineverse.presentation.designSystem.typography.LocalCineVerseTextStyle

@Composable
fun CineVerseTheme(
    state: ThemeState = ThemeState(isDark = true, onThemeChanged = {}),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        state.isDark -> darkThemeColors
        else -> lightThemeColors
    }
    CompositionLocalProvider(
        LocalThemeState provides state,
        LocalCineVerseColors provides colorScheme,
        LocalCineVerseTextStyle provides DefaultTextStyle,
    ) {
        content()
    }
}

object Theme {
    val colors: CineVerseColors
        @Composable @ReadOnlyComposable get() = LocalCineVerseColors.current

    val textStyle: CineVerseTextStyle
        @Composable @ReadOnlyComposable get() = LocalCineVerseTextStyle.current

    val state: ThemeState
        @Composable get() = LocalThemeState.current
}

val LocalThemeState = compositionLocalOf { ThemeState(false, {}) }