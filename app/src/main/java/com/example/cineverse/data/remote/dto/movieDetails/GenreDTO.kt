package com.example.cineverse.data.remote.dto.movieDetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)