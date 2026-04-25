package com.example.cineverse.presentation.loginScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineverse.data.local.dataStore.TokenStorage
import com.example.cineverse.domain.model.LoginResponse
import com.example.cineverse.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


sealed class Result<out T> {
    object Loading : Result<Nothing>()
    object Empty : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class LoginViewModel @Inject constructor(
    private val client: HttpClient,
    private val tokenStorage: TokenStorage,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authUiState = MutableStateFlow(LoginUiState())
    val authUiState: StateFlow<LoginUiState> = _authUiState

    private val _loginResponse = MutableStateFlow<Result<LoginResponse>>(Result.Empty)
    val loginResponse: StateFlow<Result<LoginResponse>> = _loginResponse


    init {
        //To avoid getting new access token every time we create this view model
        viewModelScope.launch {
            val savedAccessToken = tokenStorage.getAccessToken()
            if (savedAccessToken != null) {
                _authUiState.update {
                    it.copy(
                        accessToken = savedAccessToken,
                    )
                }
            }
        }
    }

    private suspend fun getTokenProcess(): String? {

        Log.d("Ktor", "Starting fetch...")

        val requestTokenResponse =
            withContext(Dispatchers.IO) { authRepository.fetchRequestToken(client) }

        // Save the tokens to storage!
        if (requestTokenResponse is Result.Success) {
            tokenStorage.saveAccessToken(
                accessToken = requestTokenResponse.data.requestToken,
                tokenExpiryDay = requestTokenResponse.data.expiresAt
            )
            _authUiState.update {
                it.copy(
                    accessToken = requestTokenResponse.data.requestToken,
                )
            }
            Log.d("Ktor", "Access Token= ${tokenStorage.getAccessToken()}")

            return requestTokenResponse.data.requestToken
        } else return null
    }

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            _loginResponse.value = Result.Loading
            val accessToken = getTokenProcess()
            if (accessToken != null) {
                _loginResponse.value = authRepository.login(
                    client = client,
                    username = _authUiState.value.username,
                    password = _authUiState.value.password,
                    requestToken = accessToken
                )
                Log.d("Ktor", "Login Response: ${_loginResponse.value}")
            } else {
                // 4. Handle case where token fetching failed
                _loginResponse.value = Result.Error("Failed to fetch request token")
            }
        }
    }

    fun joinAsGuest() {
        viewModelScope.launch(Dispatchers.IO) {
            val guestSessionResponse = authRepository.joinAsGuest(client)
            Log.d("Ktor", "Guest Session Response: $guestSessionResponse")

            if (guestSessionResponse is Result.Success) {
                _authUiState.update {
                    it.copy(
                        guestSessionId = guestSessionResponse.data.guestSessionId
                    )
                }
            }
        }
    }

    fun onShowResetPSBottomSheet() {
        _authUiState.update {
            it.copy(
                showResetPSBottomSheet = true
            )
        }
    }

    fun onShowSignUpBottomSheet() {
        _authUiState.update {
            it.copy(
                showSignUpBottomSheet = true
            )
        }
    }

    fun onDismissBottomSheet() {
        _authUiState.update {
            it.copy(
                showResetPSBottomSheet = false,
                showSignUpBottomSheet = false,
            )
        }
    }

    fun onUsernameChanged(username: String) {
        _authUiState.update {
            it.copy(
                username = username
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _authUiState.update {
            it.copy(
                password = password
            )
        }

    }
}