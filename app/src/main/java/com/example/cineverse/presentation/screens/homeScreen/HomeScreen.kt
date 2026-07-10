package com.example.cineverse.presentation.screens.homeScreen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.cineverse.R
import com.example.cineverse.data.remote.util.HttpErrorHandler
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.presentation.components.CineVerseLoading
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme
import com.example.cineverse.presentation.screens.homeScreen.components.HomeHeader
import com.example.cineverse.presentation.screens.homeScreen.components.HomeHeaderCarousel
import com.example.cineverse.presentation.screens.homeScreen.components.SuggestedSection
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
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

    HomeScreenContainer(
        modifier = modifier,
        onMovieClicked = onMovieClicked,
        onHeaderClicked = onHeaderClicked,
        userName = homeUiState.userName,
        lazyUpComingPagingItems = homeViewModel.upcomingMovies.collectAsLazyPagingItems(),
        lazyTopRatedPagingItems = homeViewModel.topRatedMovies.collectAsLazyPagingItems(),
        lazyNowPlayingPagingItems = homeViewModel.nowPlayingMovies.collectAsLazyPagingItems(),
        lazyPopularPagingItems = homeViewModel.popularMovies.collectAsLazyPagingItems(),
    )


}


@Composable
fun HomeScreenContainer(
    modifier: Modifier = Modifier,
    onMovieClicked: (Movie) -> Unit,
    onHeaderClicked: () -> Unit,
    userName: String,
    lazyUpComingPagingItems: LazyPagingItems<Movie>,
    lazyTopRatedPagingItems: LazyPagingItems<Movie>,
    lazyNowPlayingPagingItems: LazyPagingItems<Movie>,
    lazyPopularPagingItems: LazyPagingItems<Movie>,
) {
    val upComingRefreshState = lazyUpComingPagingItems.loadState.refresh
    val topRatedRefreshState = lazyTopRatedPagingItems.loadState.refresh
    val nowPlayingRefreshState = lazyNowPlayingPagingItems.loadState.refresh
    val popularRefreshState = lazyPopularPagingItems.loadState.refresh


    val isAnyLoading = upComingRefreshState is LoadState.Loading ||
            topRatedRefreshState is LoadState.Loading ||
            nowPlayingRefreshState is LoadState.Loading ||
            popularRefreshState is LoadState.Loading

    val firstError = listOf(
        upComingRefreshState,
        topRatedRefreshState,
        nowPlayingRefreshState,
        popularRefreshState
    ).filterIsInstance<LoadState.Error>().firstOrNull()

    when {
        isAnyLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CineVerseLoading()
            }
        }

        firstError != null -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        "Something went wrong: ${
                            HttpErrorHandler.handleException(
                                firstError.error as Exception,
                                "Unknown Error"
                            ).message
                        }",
                        style = Theme.textStyle.bodyMdMedium,
                        color = Theme.colors.brandPrimary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        lazyUpComingPagingItems.retry()
                        lazyTopRatedPagingItems.retry()
                        lazyNowPlayingPagingItems.retry()
                        lazyPopularPagingItems.retry()
                    }) {
                        Text("Retry")
                    }
                }
            }
        }

        else -> {

            Box(
                modifier = modifier
                    .fillMaxSize()
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

                    if (topRatedRefreshState is LoadState.NotLoading) {
                        item {
                            HomeHeaderCarousel(
                                lazyPagingItems = lazyTopRatedPagingItems,
                                onClickMovie = { onMovieClicked(it) }
                            )
                        }
                    }

                    if (nowPlayingRefreshState is LoadState.NotLoading) {
                        item {
                            SuggestedSection(
                                title = stringResource(id = R.string.recently_released),
                                lazyPagingItems = lazyNowPlayingPagingItems,
                                onMovieClicked = { onMovieClicked(it) }
                            )
                        }
                    }

                    if (upComingRefreshState is LoadState.NotLoading) {
                        item {
                            SuggestedSection(
                                title = stringResource(R.string.upcoming_movies),
                                lazyPagingItems = lazyUpComingPagingItems,
                                onMovieClicked = { onMovieClicked(it) }
                            )
                        }
                    }

                    if (popularRefreshState is LoadState.NotLoading) {
                        item {
                            SuggestedSection(
                                title = stringResource(R.string.matches_your_vibe),
                                lazyPagingItems = lazyPopularPagingItems,
                                onMovieClicked = { onMovieClicked(it) }
                            )
                        }
                    }

                    if (topRatedRefreshState is LoadState.NotLoading) {
                        item {
                            SuggestedSection(
                                title = stringResource(R.string.top_rated_tv_shows),
                                lazyPagingItems = lazyTopRatedPagingItems,
                                onMovieClicked = { onMovieClicked(it) }
                            )
                        }
                    }

                    if (popularRefreshState is LoadState.NotLoading) {
                        item {
                            SuggestedSection(
                                title = stringResource(R.string.you_recently_viewed),
                                lazyPagingItems = lazyPopularPagingItems,
                                onMovieClicked = { onMovieClicked(it) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    val lazyPagingItems = flow<PagingData<Movie>> {}.collectAsLazyPagingItems()

    CineVerseTheme {
        HomeScreenContainer(
            userName = "Ali Gamal",
            onMovieClicked = {},
            onHeaderClicked = {},
            lazyUpComingPagingItems = lazyPagingItems,
            lazyTopRatedPagingItems = lazyPagingItems,
            lazyNowPlayingPagingItems = lazyPagingItems,
            lazyPopularPagingItems = lazyPagingItems,

            )
    }
}
