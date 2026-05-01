package com.example.cineverse.data.remote.dto.nowPlayingDto


import com.example.cineverse.data.remote.dto.DatesDto
import com.example.cineverse.data.remote.dto.ResultedMovieDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingMoviesDTO(
    @SerialName("dates")
    val dates: DatesDto,
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val resultedNowPlayingMovieDtos: List<ResultedMovieDto>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)