package com.example.cineverse.presentation.screens.homeScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.presentation.components.MovieCard
import com.example.cineverse.presentation.components.ShowMoreRow
import kotlinx.coroutines.launch


@Composable
fun SuggestedSection(
    modifier: Modifier = Modifier,
    title: String,
    moviesList: List<Movie>,
    initialItemsCount: Int = 3,
    onMovieClicked: (Movie) -> Unit
) {
    val listState = rememberLazyListState()
    val listCoroutineScope = rememberCoroutineScope()
    var isExpanded by remember { mutableStateOf(false) }

    val displayedMovies = remember(moviesList, isExpanded) {
        if (isExpanded) moviesList else moviesList.take(initialItemsCount)
    }

    val shouldShowMoreButton = remember(moviesList) {
        moviesList.size > initialItemsCount
    }

    val handleShowMore = {
        isExpanded = true
        listCoroutineScope.launch {
            listState.animateScrollToItem(initialItemsCount)
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
                items = displayedMovies,
                key = { movie -> movie.id }
            ) { movie ->
                MovieCard(
                    movie = movie,
                    onMovieClicked = { onMovieClicked(movie) }
                )
            }
        }
    }
}