package com.example.cineverse.data.remote.dto.upComingDto

import com.example.cineverse.data.remote.dto.ResultedMovieDto
import com.example.cineverse.data.remote.dto.DatesDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpComingResponseDTO(
    @SerialName("dates")
    val dates: DatesDto,
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val resultedUpComingMovieDtos: List<ResultedMovieDto>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)