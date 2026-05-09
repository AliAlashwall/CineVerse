package com.example.cineverse.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Gravatar(
    @SerialName("hash")
    val hash: String
)