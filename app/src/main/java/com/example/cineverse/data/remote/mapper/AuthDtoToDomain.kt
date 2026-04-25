package com.example.cineverse.data.remote.mapper

import com.example.cineverse.data.remote.dto.GuestSessionResponseDTO
import com.example.cineverse.data.remote.dto.LoginResponseDTO
import com.example.cineverse.data.remote.dto.RequestTokenResponseDTO
import com.example.cineverse.domain.model.GuestSessionResponse
import com.example.cineverse.domain.model.LoginResponse
import com.example.cineverse.domain.model.TokenResponse



fun RequestTokenResponseDTO.toDomain() = TokenResponse(
    expiresAt = expiresAt,
    requestToken = requestToken,
    success = success
)

fun LoginResponseDTO.toDomain() = LoginResponse(
    expiresAt = expiresAt,
    requestToken = requestToken,
    success = success
)

fun GuestSessionResponseDTO.toDomain() = GuestSessionResponse(
    expiresAt = expiresAt,
    guestSessionId = guestSessionId,
    success = success
)