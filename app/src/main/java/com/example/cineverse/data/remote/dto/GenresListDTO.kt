package com.example.cineverse.data.remote.dto


import com.example.cineverse.data.remote.dto.movieDetails.GenreDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresListDTO(
    @SerialName("genres")
    val genres: List<GenreDTO>
)