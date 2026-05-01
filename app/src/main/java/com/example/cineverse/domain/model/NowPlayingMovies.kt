package com.example.cineverse.domain.model

data class NowPlayingMovies(
    val dates: MovieDates,
    val page: Int,
    val resultedMovies: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)