package com.example.cineverse.presentation.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cineverse.R
import com.example.cineverse.domain.model.mockMoviesList
import com.example.cineverse.navigation.EmptyRoute
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme
import com.example.cineverse.presentation.screens.homeScreen.components.HomeHeader
import com.example.cineverse.presentation.screens.homeScreen.components.HomeHeaderCarousel
import com.example.cineverse.presentation.screens.homeScreen.components.SuggestedSection

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Theme.colors.backgroundScreen),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            HomeHeader(userName = "Ali Gamal", modifier = Modifier.clickable {
                navController.navigate(EmptyRoute)
            })
        }
        item {
            HomeHeaderCarousel(
                moviesList = mockMoviesList,
                onClickMovie = {/*TO-DO*/ }
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


@Preview
@Composable
private fun HomeScreenPreview() {
    CineVerseTheme {
        val navController = rememberNavController()
        HomeScreen(navController = navController)
    }
}
