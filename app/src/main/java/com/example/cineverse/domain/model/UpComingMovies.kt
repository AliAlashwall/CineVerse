package com.example.cineverse.domain.model


data class UpComingMovies(
    val dates: MovieDates,
    val page: Int,
    val resultedMovies: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)