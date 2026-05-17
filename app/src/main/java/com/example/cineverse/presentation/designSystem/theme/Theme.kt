package com.example.cineverse.presentation.designSystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.cineverse.presentation.designSystem.colors.CineVerseColors
import com.example.cineverse.presentation.designSystem.colors.LocalCineVerseColors
import com.example.cineverse.presentation.designSystem.colors.darkThemeColors
import com.example.cineverse.presentation.designSystem.colors.lightThemeColors
import com.example.cineverse.presentation.designSystem.typography.CineVerseTextStyle
import com.example.cineverse.presentation.designSystem.typography.DefaultTextStyle
import com.example.cineverse.presentation.designSystem.typography.LocalCineVerseTextStyle

@Composable
fun CineVerseTheme(
    state: ThemeState = rememberThemeState(isDark = isSystemInDarkTheme()),
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

@Composable
fun rememberThemeState(
    isDark: Boolean = isSystemInDarkTheme(),
): ThemeState {
    val isDarkState = remember { mutableStateOf(isDark) }

    SideEffect {
        isDarkState.value = isDark
    }
    return remember(isDarkState.value) {
        ThemeState(
            isDark = isDarkState.value,
            onThemeChanged = { isDarkState.value = it }
        )
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
