package com.example.cineverse.data.remote.dto.movieDetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponseDTO(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<ReviewDTO>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)
@Serializable
data class ReviewDTO(
    @SerialName("author")
    val author: String,
    @SerialName("author_details")
    val authorDetails: AuthorDetailsDTO,
    @SerialName("content")
    val content: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("id")
    val id: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("url")
    val url: String
)
