package com.example.cineverse.data.remote.dto.movieDetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDetailsDTO(
    @SerialName("name")
    val name: String,
    @SerialName("username")
    val username: String,
    @SerialName("avatar_path")
    val avatarPath: String?,
    @SerialName("rating")
    val rating: Double?
)
