package com.example.cineverse.presentation.screens.exploreScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun GenreChip(
    name: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) Theme.colors.brandPrimary.copy(alpha = 0.1f) else Color.Transparent)
            .border(
                width = 1.dp,
                color = if (isSelected) Theme.colors.brandPrimary else Theme.colors.backgroundCard,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            style = Theme.textStyle.bodySmMedium,
            color = if (isSelected) Theme.colors.brandPrimary else Theme.colors.shadeTertiary
        )
    }
}