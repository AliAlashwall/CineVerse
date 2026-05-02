package com.example.cineverse.presentation.screens.exploreScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun ExploreScreen(modifier: Modifier = Modifier) {
    // TO-DO: implement the Explore screen

    Text(
        text = "Explore Screen", modifier = modifier.fillMaxSize(),
        style = Theme.textStyle.titleLg,
        color = Theme.colors.brandPrimary
    )

}