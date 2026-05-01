package com.example.cineverse.presentation.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cineverse.R
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.domain.model.mockMoviesList
import com.example.cineverse.presentation.components.CineVerseErrorScreen
import com.example.cineverse.presentation.components.CineVerseLoading
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme
import com.example.cineverse.presentation.screens.homeScreen.components.HomeHeader
import com.example.cineverse.presentation.screens.homeScreen.components.HomeHeaderCarousel
import com.example.cineverse.presentation.screens.homeScreen.components.SuggestedSection

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()
    val homeEvent by homeViewModel.homeEvent.collectAsStateWithLifecycle()


    when (homeEvent) {
        HomeEvent.Loading -> { CineVerseLoading() }

        HomeEvent.Success -> {
            HomeScreenContainer(
                modifier = modifier,
                onClickCarouselMovie = {},
                userName = homeUiState.userName,
                upComingMovies = homeUiState.upcomingMovies,
                topRatedMovies = homeUiState.topRatedMovies,
                nowPlayingMovies = homeUiState.nowPlayingMovies,
                popularMovies = homeUiState.popularMovies
            )
        }

        HomeEvent.Error -> {
            CineVerseErrorScreen(
                onTryAgain = {}
            )
        }

    }


}


@Composable
fun HomeScreenContainer(
    modifier: Modifier = Modifier,
    onClickCarouselMovie: () -> Unit,
    userName: String,
    upComingMovies: List<Movie>,
    topRatedMovies: List<Movie>,
    nowPlayingMovies: List<Movie>,
    popularMovies: List<Movie>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Theme.colors.backgroundScreen),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            HomeHeader(userName = userName)
        }
        item {
            HomeHeaderCarousel(
                moviesList = upComingMovies,
                onClickMovie = { onClickCarouselMovie() }
            )
        }
        item {
            // Now Playing Section
            SuggestedSection(
                title = stringResource(id = R.string.recently_released),
                moviesList = nowPlayingMovies
            )
        }

        item {
            // Up coming Section
            SuggestedSection(
                title = stringResource(R.string.upcoming_movies),
                moviesList = upComingMovies
            )
        }

        item {
            // Popular Section
            SuggestedSection(
                title = stringResource(R.string.matches_your_vibe),
                moviesList = popularMovies
            )
        }

        item {
            // Top Rated Section
            SuggestedSection(
                title = stringResource(R.string.top_rated_tv_shows),
                moviesList = topRatedMovies
            )
        }

        item {
            // Temporal until get the recently viewed movies
            SuggestedSection(
                title = stringResource(R.string.you_recently_viewed),
                moviesList = popularMovies
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {

    CineVerseTheme {
        HomeScreenContainer(
            userName = "Ali Gamal",
            onClickCarouselMovie = {},
            upComingMovies = mockMoviesList,
            topRatedMovies = mockMoviesList,
            nowPlayingMovies = mockMoviesList,
            popularMovies = mockMoviesList
        )
    }
}
