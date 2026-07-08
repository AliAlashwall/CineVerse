package com.example.cineverse.presentation.screens.exploreScreen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cineverse.R
import com.example.cineverse.domain.model.Genre
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.presentation.components.CineVerseLoading
import com.example.cineverse.presentation.components.MovieCard
import com.example.cineverse.presentation.components.MoviePoster
import com.example.cineverse.presentation.components.RatingBadge
import com.example.cineverse.presentation.components.SearchBar
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme
import com.example.cineverse.presentation.screens.exploreScreen.components.ExploreTabs
import com.example.cineverse.presentation.screens.exploreScreen.components.GenreChip
import com.example.cineverse.presentation.screens.exploreScreen.components.ViewModeToggle

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = hiltViewModel(),
    onMovieClicked: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Theme.colors.backgroundScreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            SearchBar(
                query = uiState.searchQuery,
                onQueryChange = viewModel::onSearchQueryChange
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Tabs
            ExploreTabs(
                selectedTab = uiState.selectedTab,
                onTabSelected = viewModel::onTabSelected
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Genres
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                item {
                    val genre = Genre(id = 0, name = "All")
                    GenreChip(
                        name = genre.name,
                        isSelected = uiState.selectedGenreId == genre.id,
                        onClick = { viewModel.onGenreSelected(genre.id) }
                    )
                }
                items(uiState.genres) { genre ->
                    GenreChip(
                        name = genre.name,
                        isSelected = uiState.selectedGenreId == genre.id,
                        onClick = { viewModel.onGenreSelected(genre.id) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Content
            if (uiState.viewMode == ViewMode.GRID) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 80.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.filteredMovies) { movie ->
                        MovieCard(
                            movie = movie,
                            modifier = Modifier.fillMaxWidth(),
                            onMovieClicked = { onMovieClicked(movie.id) }
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 80.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.filteredMovies) { movie ->
                        MovieListItem(
                            movie = movie,
                            onMovieClicked = { onMovieClicked(movie.id) }
                        )
                    }
                }
            }
        }

        // View Mode Toggle Button
        ViewModeToggle(
            viewMode = uiState.viewMode,
            onToggle = viewModel::onViewModeToggle,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 24.dp, end = 16.dp)
        )

        if (uiState.isLoading){
            CineVerseLoading()
        }


    }
}


@Composable
fun MovieListItem(
    movie: Movie,
    onMovieClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .clickable { onMovieClicked(movie.id) },

        ) {
        Box(
            modifier = Modifier
                .width(64.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(12.dp))
        ) {
            MoviePoster(
                posterPath = movie.posterPath,
                modifier = Modifier.fillMaxSize()
            )
        }

        Column(
            modifier = Modifier
                .padding(12.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = movie.title,
                    style = Theme.textStyle.bodyMdMedium,
                    color = Theme.colors.shadePrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,

                    )
                RatingBadge(rating = movie.voteAverage.toString())
            }

            Text(
                text = "Drama, Action, Crime, Thriller", // This should come from movie genres
                style = Theme.textStyle.bodySmRegular,
                color = Theme.colors.shadeTertiary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.due_tone_clock),
                    contentDescription = null,
                    tint = Theme.colors.shadeTertiary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "2h 32m", // Mock duration
                    style = Theme.textStyle.labelMdRegular,
                    color = Theme.colors.shadeTertiary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = painterResource(id = R.drawable.due_tone_calendar),
                    contentDescription = null,
                    tint = Theme.colors.shadeTertiary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = movie.releaseDate,
                    style = Theme.textStyle.labelMdRegular,
                    color = Theme.colors.shadeTertiary
                )
            }
        }
    }
}


@Preview(
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
private fun ExploreScreenPreview() {
    CineVerseTheme {
        ExploreScreen {}
    }
}