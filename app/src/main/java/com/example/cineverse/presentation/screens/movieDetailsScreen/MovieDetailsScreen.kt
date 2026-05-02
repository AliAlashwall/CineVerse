package com.example.cineverse.presentation.screens.movieDetailsScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cineverse.R
import com.example.cineverse.domain.model.Cast
import com.example.cineverse.domain.model.mockCastList
import com.example.cineverse.presentation.components.MoviePoster
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme
import com.example.cineverse.util.toRuntimeFormat


@Composable
fun MovieDetailsScreen(
    movieDetailsViewModel: MovieDetailsViewModel,
    movieId: Int,
    navController: NavController,
    onAddClicked: () -> Unit = {},
    onPlayClicked: () -> Unit = {}
) {
    LaunchedEffect(Unit) {
        movieDetailsViewModel.getMovieDetails(movieId)
    }
    val movieUiState by movieDetailsViewModel.movieUiState.collectAsStateWithLifecycle()



    MovieDetailsContainer(
        onBackClicked = { navController.popBackStack() },
        onPlayClicked = { onPlayClicked() },
        onAddClicked = { onAddClicked() },
        posterPath = movieUiState.movieDetails?.posterPath,
        movieName = movieUiState.movieDetails?.title ?: "",
        movieCategory = movieUiState.movieDetails?.genres?.map { genre -> genre.name + " " }
            .toString().replace("[", "").replace("]", ""),
        movieRate = movieUiState.movieDetails?.voteAverage.toString(),
        movieDuration = movieUiState.movieDetails?.runtime?.toRuntimeFormat() ?: "0h 0mm",
        dateCreated = movieUiState.movieDetails?.releaseDate.toString(),
        storyLineContent = movieUiState.movieDetails?.overview ?: "",
        castList = movieUiState.movieDetails?.credits?.cast ?: emptyList()
    )
}

@Composable
fun MovieDetailsContainer(
    posterPath: String?,
    movieName: String,
    movieCategory: String,
    movieRate: String,
    movieDuration: String,
    dateCreated: String,
    storyLineContent: String,
    castList: List<Cast>,
    onBackClicked: () -> Unit,
    onPlayClicked: () -> Unit,
    onAddClicked: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.backgroundScreen)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onBackClicked() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.outline_arrow_left),
                        contentDescription = stringResource(R.string.back_arrow),
                        modifier = Modifier.size(24.dp),
                        tint = Theme.colors.shadePrimary
                    )
                }

            }
        }
        item {
            MoviePoster(
                posterPath = posterPath,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(0.75f)
            )
        }
        item {
            MovieOverViewCard(
                movieName = movieName,
                movieCategory = movieCategory,
                movieRate = movieRate,
                movieDuration = movieDuration,
                dateCreated = dateCreated,
                onPlayClicked = onPlayClicked,
                onAddClicked = onAddClicked
            )
        }

        item {
            StorylineSection(
                storyLineContent = storyLineContent,
                modifier = Modifier.padding(top = 12.dp)
            )


        }

        item {
            StarCastSection(castList = castList, modifier = Modifier.padding(top = 12.dp))
        }
    }
}


@Preview
@Composable
private fun MovieDetailsPreview() {
    CineVerseTheme {
        MovieDetailsContainer(
            onBackClicked = { /* Handle back navigation */ },
            onPlayClicked = { /* Handle play action */ },
            onAddClicked = { /* Handle add to watchlist action */ },
            posterPath = "/8nytsqL59SFJTVYVrN72k6qkGgJ.jpg",
            movieName = "The Dark Knight",
            movieCategory = "Drama, Action, Crime, Thriller",
            movieRate = "8.5",
            movieDuration = "2h 32m",
            dateCreated = "2008, Jul 18",
            storyLineContent = "Batman raises the stakes in his war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent, Batman sets out to",
            castList = mockCastList
        )
    }
}