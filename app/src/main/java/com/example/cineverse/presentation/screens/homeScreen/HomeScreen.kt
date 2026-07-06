package com.example.cineverse.presentation.screens.homeScreen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    onMovieClicked: (Movie) -> Unit,
    onHeaderClicked: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var backPressedOnce by remember { mutableStateOf(false) }

    BackHandler(enabled = !backPressedOnce) {
        backPressedOnce = true
        Toast.makeText(
            context,
            "press back again to exit",
            Toast.LENGTH_SHORT
        ).show()

        scope.launch {
            delay(2000)
            backPressedOnce = false
        }
    }
    val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()
    val homeEvent by homeViewModel.homeEvent.collectAsStateWithLifecycle()


    when (homeEvent) {
        HomeEvent.Loading -> {
            CineVerseLoading()
        }

        HomeEvent.Success -> {
            HomeScreenContainer(
                modifier = modifier,
                onMovieClicked = onMovieClicked,
                onHeaderClicked = onHeaderClicked,
                userName = homeUiState.userName,
                upComingMovies = homeUiState.upcomingMovies,
                topRatedMovies = homeUiState.topRatedMovies,
                nowPlayingMovies = homeUiState.nowPlayingMovies,
                popularMovies = homeUiState.popularMovies
            )
        }

        is HomeEvent.Error -> {
            CineVerseErrorScreen(
                onTryAgain = { homeViewModel.loadMovies() }
            )
        }

    }


}


@Composable
fun HomeScreenContainer(
    modifier: Modifier = Modifier,
    onMovieClicked: (Movie) -> Unit,
    onHeaderClicked: () -> Unit,
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
            HomeHeader(userName = userName, onClick = { onHeaderClicked() })
        }

        if (upComingMovies.isNotEmpty()) {
            item {
                HomeHeaderCarousel(
                    moviesList = upComingMovies,
                    onClickMovie = { onMovieClicked(it) }
                )
            }
        }

        if (nowPlayingMovies.isNotEmpty()) {
            item {
                // Recently Released Section
                SuggestedSection(
                    title = stringResource(id = R.string.recently_released),
                    moviesList = nowPlayingMovies,
                    onMovieClicked = { onMovieClicked(it) }
                )
            }
        }

        if (upComingMovies.isNotEmpty()) {
            item {
                // Up coming Section
                SuggestedSection(
                    title = stringResource(R.string.upcoming_movies),
                    moviesList = upComingMovies,
                    onMovieClicked = { onMovieClicked(it) }
                )
            }
        }

        if (popularMovies.isNotEmpty()) {
            item {
                // Popular Section
                SuggestedSection(
                    title = stringResource(R.string.matches_your_vibe),
                    moviesList = popularMovies,
                    onMovieClicked = { onMovieClicked(it) }
                )
            }
        }

        if (topRatedMovies.isNotEmpty()) {
            item {
                // Top Rated Section
                SuggestedSection(
                    title = stringResource(R.string.top_rated_tv_shows),
                    moviesList = topRatedMovies,
                    onMovieClicked = { onMovieClicked(it) }
                )
            }
        }

        if (popularMovies.isNotEmpty()) {
            item {
                // Temporal until get the recently viewed movies
                SuggestedSection(
                    title = stringResource(R.string.you_recently_viewed),
                    moviesList = popularMovies,
                    onMovieClicked = { onMovieClicked(it) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {

    CineVerseTheme {
        HomeScreenContainer(
            userName = "Ali Gamal",
            onMovieClicked = {},
            upComingMovies = mockMoviesList,
            topRatedMovies = mockMoviesList,
            nowPlayingMovies = mockMoviesList,
            popularMovies = mockMoviesList,
            onHeaderClicked = {}
        )
    }
}
