package com.example.cineverse.presentation.screens.movieDetailsScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme


@Composable
fun MovieDetailsScreen(movieDetailsViewModel: MovieDetailsViewModel) {
    LaunchedEffect(Unit) {
        movieDetailsViewModel.getMovieDetails(1226863)
    }
}

@Preview
@Composable
private fun MovieDetailsPreview() {
    CineVerseTheme {
//        MovieDetailsScreen()
    }
}