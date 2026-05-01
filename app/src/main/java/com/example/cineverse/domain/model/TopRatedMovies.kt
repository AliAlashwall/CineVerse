package com.example.cineverse.domain.model

data class TopRatedMovies(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)