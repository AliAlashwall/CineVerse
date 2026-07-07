package com.example.cineverse.presentation.screens.matchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun MatchScreen() {

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Coming Soon",
            style = Theme.textStyle.titleLg,
            color = Theme.colors.brandPrimary
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Match Screen",
            style = Theme.textStyle.titleMd,
            color = Theme.colors.buttonOnPrimary
        )
    }

}

@Preview
@Composable
private fun MatchScreenPreview() {
    CineVerseTheme { MatchScreen() }
}