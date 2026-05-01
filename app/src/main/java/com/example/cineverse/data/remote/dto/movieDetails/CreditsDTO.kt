package com.example.cineverse.data.remote.dto.movieDetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsDTO(
    @SerialName("cast")
    val cast: List<CastDTO>,
    @SerialName("crew")
    val crew: List<CrewDTO>
)