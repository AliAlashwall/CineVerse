package com.example.cineverse.data.remote.dto.topRated


import com.example.cineverse.data.remote.dto.ResultedMovieDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopRatedMoviesDto(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<ResultedMovieDto>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)