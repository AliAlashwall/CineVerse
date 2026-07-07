package com.example.cineverse.presentation.screens.exploreScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.presentation.designSystem.theme.Theme
import com.example.cineverse.presentation.screens.exploreScreen.ExploreTab

@Composable
fun ExploreTabs(
    selectedTab: ExploreTab,
    onTabSelected: (ExploreTab) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TabItem(
                text = stringResource(R.string.movies),
                isSelected = selectedTab == ExploreTab.MOVIES,
                onClick = { onTabSelected(ExploreTab.MOVIES) }
            )
            TabItem(
                text = stringResource(R.string.series),
                isSelected = selectedTab == ExploreTab.SERIES,
                onClick = { onTabSelected(ExploreTab.SERIES) }
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Theme.colors.backgroundCard
        )
    }
}