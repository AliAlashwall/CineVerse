package com.example.cineverse.data.remote.mapper

import com.example.cineverse.data.remote.dto.movieDetails.ReviewDTO
import com.example.cineverse.domain.model.Review

fun ReviewDTO.toDomain(): Review {
    return Review(
        id = this.id,
        author = this.authorDetails.name.ifEmpty { this.author },
        username = this.authorDetails.username,
        avatarPath = this.authorDetails.avatarPath,
        content = this.content,
        createdAt = this.createdAt,
        rating = this.authorDetails.rating
    )
}
