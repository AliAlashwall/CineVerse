package com.example.cineverse.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun CineVerseLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Theme.colors.backgroundScreen),
        contentAlignment = Alignment.Center
    ) {
        AnimatedLoading(
            tintColor = Theme.colors.brandPrimary,
            modifier = Modifier.size(100.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CineVerseLoadingPreview() {
    CineVerseTheme { CineVerseLoading() }
}