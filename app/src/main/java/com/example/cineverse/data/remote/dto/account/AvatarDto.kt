package com.example.cineverse.data.remote.dto.account


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvatarDto(
    @SerialName("gravatar")
    val gravatar: GravatarDto,
    @SerialName("tmdb")
    val tmdb: TmdbDto
)