package com.example.cineverse.data.remote.dto.account


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbDto(
    @SerialName("avatar_path")
    val avatarPath: String?
)