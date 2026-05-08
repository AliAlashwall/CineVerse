package com.example.cineverse.domain.model

data class Review(
    val id: String,
    val author: String,
    val username: String,
    val avatarPath: String?,
    val content: String,
    val createdAt: String,
    val rating: Double?
)
