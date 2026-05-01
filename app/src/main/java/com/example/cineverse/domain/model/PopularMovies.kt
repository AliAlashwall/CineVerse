package com.example.cineverse.domain.model

data class PopularMovies(
    val page: Int,
    val resultedMovies: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)