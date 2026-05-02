package com.example.cineverse.presentation.screens.profileScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    // TO-DO: implement the Profile screen
    Text(text = "Profile Screen", modifier = modifier.fillMaxSize(),
        style = Theme.textStyle.titleLg,
        color = Theme.colors.brandPrimary
    )

}