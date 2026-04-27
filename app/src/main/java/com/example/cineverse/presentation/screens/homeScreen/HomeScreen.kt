package com.example.cineverse.presentation.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.domain.model.mockMoviesList
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme
import com.example.cineverse.presentation.screens.homeScreen.components.HomeHeader
import com.example.cineverse.presentation.screens.homeScreen.components.HomeHeaderCarousel
import com.example.cineverse.presentation.screens.homeScreen.components.SuggestedSection

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = { CineVerseBottomBar() },
        containerColor = Theme.colors.backgroundScreen
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                HomeHeader(userName = "Ali Gamal")
            }
            item {
                HomeHeaderCarousel(
                    moviesList = mockMoviesList,
                    onClickMovie = {/*TO-DO*/}
                )
            }
            item {
                SuggestedSection(
                    title = stringResource(id = R.string.recently_released),
                    moviesList = mockMoviesList,
                    onShowMoreClicked = {}
                )
            }

            item {
                SuggestedSection(
                    title = stringResource(R.string.upcoming_movies),
                    moviesList = mockMoviesList,
                    onShowMoreClicked = {}
                )
            }

            item {
                SuggestedSection(
                    title = stringResource(R.string.matches_your_vibe),
                    moviesList = mockMoviesList,
                    onShowMoreClicked = {}
                )
            }

            item {
                SuggestedSection(
                    title = stringResource(R.string.top_rated_tv_shows),
                    moviesList = mockMoviesList,
                    onShowMoreClicked = {}
                )
            }

            item {
                SuggestedSection(
                    title = stringResource(R.string.you_recently_viewed),
                    moviesList = mockMoviesList,
                    onShowMoreClicked = {}
                )
            }
        }
    }
}

@Composable
fun CineVerseBottomBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Theme.colors.backgroundCard)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(
            icon = R.drawable.due_tone_home,
            label = stringResource(id = R.string.home),
            isSelected = true
        )
        BottomNavItem(
            icon = R.drawable.outline_search,
            label = stringResource(id = R.string.explore),
            isSelected = false
        )
        BottomNavItem(
            icon = R.drawable.outline_magic_stick,
            label = stringResource(id = R.string.match),
            isSelected = false
        )
        BottomNavItem(
            icon = R.drawable.outline_user,
            label = stringResource(id = R.string.me),
            isSelected = false
        )
    }
}

@Composable
fun BottomNavItem(
    icon: Int,
    label: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(if (isSelected) Theme.colors.brandSecondary else Color.Transparent)
                .padding(horizontal = 16.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(24.dp),
                tint = if (isSelected) Theme.colors.brandPrimary else Theme.colors.shadeSecondary
            )
        }
        Text(
            text = label,
            style = Theme.textStyle.labelMdMedium,
            color = if (isSelected) Theme.colors.brandPrimary else Theme.colors.shadeSecondary
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    CineVerseTheme {
        HomeScreen()
    }
}
