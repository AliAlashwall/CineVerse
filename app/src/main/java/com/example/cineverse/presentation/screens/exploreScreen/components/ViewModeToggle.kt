package com.example.cineverse.presentation.screens.exploreScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.presentation.designSystem.theme.Theme
import com.example.cineverse.presentation.screens.exploreScreen.ViewMode

@Composable
fun ViewModeToggle(
    viewMode: ViewMode,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Theme.colors.backgroundCard)
            .clickable { onToggle() }
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = if (viewMode == ViewMode.GRID) R.drawable.grid_view_active else R.drawable.grid_view_not_active
                ),
                contentDescription = "Grid View",
                tint = if (viewMode == ViewMode.GRID) Theme.colors.brandPrimary else Theme.colors.shadeTertiary,
                modifier = Modifier.size(24.dp)
            )
            Icon(
                painter = painterResource(
                    id = if (viewMode == ViewMode.LIST) R.drawable.list_view_active else R.drawable.list_view_not_active
                ),
                contentDescription = "List View",
                tint = if (viewMode == ViewMode.LIST) Theme.colors.brandPrimary else Theme.colors.shadeTertiary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}