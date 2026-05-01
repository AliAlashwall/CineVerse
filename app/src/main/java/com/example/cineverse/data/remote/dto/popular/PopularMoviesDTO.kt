package com.example.cineverse.data.remote.dto.popular


import com.example.cineverse.data.remote.dto.ResultedMovieDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularMoviesDTO(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val resultedPopularMoviesDto: List<ResultedMovieDto>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)