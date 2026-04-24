package com.example.cineverse.presentation.designSystem.colors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


data class CineVerseColors(
    val backgroundScreen: Color,
    val shadePrimary: Color,
    val backgroundCard: Color,
    val brandPrimary: Color,
    val buttonPrimary: Color,
    val buttonSecondary: Color,
    val brandSecondary: Color,
    val brandTertiary: Color,
    val backgroundBottomSheet: Color,
    val backgroundBottomSheetCard: Color,
    val buttonDisabled: Color,
    val buttonOnPrimary: Color,
    val buttonOnSecondary: Color,
    val buttonOnTertiary: Color,
    val buttonOnDisabled: Color,
    val strokePrimary: Color,
    val additionalPrimaryRed: Color,
    val additionalSecondaryRed: Color,
    val additionalSecondaryGreen: Color,
    val additionalSecondaryYellow: Color,
    val additionalSecondaryShimmer: Color,
    val additionalPrimaryGreen: Color,
    val additionalPrimaryYellow: Color,
    val overlayPrimary: Color,
    val overlaySecondary: Color,
    val additionalTEMP: Color,
    val shadeSecondary: Color,
    val shadeTertiary: Color,
    val shadeQuaternary: Color,
    val shadeQuinary: Color
)


val LocalCineVerseColors = staticCompositionLocalOf { darkThemeColors }