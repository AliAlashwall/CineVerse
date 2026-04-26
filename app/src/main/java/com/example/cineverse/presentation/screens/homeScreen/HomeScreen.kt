package com.example.cineverse.presentation.screens.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme

@Composable
fun HomeScreen() {
    Column {
        Text(text = "Home Screen")
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    CineVerseTheme {
        HomeScreen()
    }
}