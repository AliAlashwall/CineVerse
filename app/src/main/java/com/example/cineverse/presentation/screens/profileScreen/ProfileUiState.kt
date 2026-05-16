package com.example.cineverse.presentation.screens.profileScreen

data class ProfileUiState(
    val sessionId: String = "null",
    val userName: String? = null,
    val name: String? = null,
    val gravatar: String = "",
    val avatarPath: String? = null
)
