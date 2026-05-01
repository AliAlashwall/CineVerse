package com.example.cineverse.presentation.screens.homeScreen


import com.example.cineverse.domain.model.Movie

data class HomeUiState(
    val userName: String = "Ali Gamal",
    val isLoading: Boolean = false,
    val guestId: String? = null,
    val upcomingMovies: List<Movie> = emptyList(),
    val topRatedMovies: List<Movie> = emptyList(),
    val nowPlayingMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
)
