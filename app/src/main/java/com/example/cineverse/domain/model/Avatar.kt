package com.example.cineverse.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Avatar(
    @SerialName("gravatar")
    val gravatar: Gravatar,
    @SerialName("tmdb")
    val tmdb: Tmdb
)