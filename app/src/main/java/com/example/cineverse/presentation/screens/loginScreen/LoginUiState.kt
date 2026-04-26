package com.example.cineverse.presentation.screens.loginScreen


data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val accessToken: String? = null,
    val isLoggedIn: Boolean = false,
    val guestSessionId: String? = null,
    val viewModelLoading: Boolean = true,
    val showResetPSBottomSheet: Boolean = false,
    val showSignUpBottomSheet: Boolean = false
)