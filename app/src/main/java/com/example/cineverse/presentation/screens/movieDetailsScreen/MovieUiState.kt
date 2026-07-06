package com.example.cineverse.presentation.screens.movieDetailsScreen

import com.example.cineverse.domain.model.Movie
import com.example.cineverse.domain.model.MovieDetails

data class MovieUiState(
    val movieDetails: MovieDetails? = null,
    val popularMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingDetails: Boolean = false,
    val error: String? = null,
    val detailsError: String? = null
)
