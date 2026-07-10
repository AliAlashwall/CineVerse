package com.example.cineverse.presentation.screens.homeScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.presentation.components.CineVerseLoading
import com.example.cineverse.presentation.components.FailedToLoadMore
import com.example.cineverse.presentation.components.LoadingMovieCard
import com.example.cineverse.presentation.components.MovieCard
import com.example.cineverse.presentation.components.ShowMoreRow
import kotlinx.coroutines.launch


@Composable
fun SuggestedSection(
    modifier: Modifier = Modifier,
    title: String,
    lazyPagingItems: LazyPagingItems<Movie>,
    initialItemsCount: Int = 3,
    onMovieClicked: (Movie) -> Unit
) {
    val listState = rememberLazyListState()
    val listCoroutineScope = rememberCoroutineScope()
    var isExpanded by remember { mutableStateOf(false) }


    val shouldShowMoreButton = remember(lazyPagingItems) {
        lazyPagingItems.itemCount > initialItemsCount
    }

    val handleShowMore = {
        if (!isExpanded) { // to Avoid calling it twice
            isExpanded = true
            listCoroutineScope.launch {
                listState.animateScrollToItem(initialItemsCount)
            }
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        ShowMoreRow(
            title = title,
            shouldShowMoreButton = shouldShowMoreButton,
            handleShowMore = { handleShowMore() }
        )
        LazyRow(
            state = listState,
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(
                count = lazyPagingItems.itemCount.coerceAtMost(if (isExpanded) Int.MAX_VALUE else initialItemsCount),
                key = { index ->
                    val movie = lazyPagingItems.peek(index)
                    movie?.let { "${it.id}_$index" } ?: index
                },
            ) { index ->
                val movie = lazyPagingItems[index]
                if (movie != null) {
                    MovieCard(
                        movie = movie,
                        modifier = Modifier.fillMaxWidth(),
                        onMovieClicked = { onMovieClicked(movie) }
                    )
                } else {
                    LoadingMovieCard()
                }
            }

            when (lazyPagingItems.loadState.append) {
                is LoadState.Loading -> {
                    item {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CineVerseLoading()
                        }
                    }
                }

                is LoadState.Error -> {
                    item {
                        FailedToLoadMore(
                            onClick = { lazyPagingItems.retry() }
                        )
                    }
                }

                else -> {}
            }
        }
    }
}