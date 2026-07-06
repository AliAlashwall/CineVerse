package com.example.cineverse.presentation.screens.loginScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineverse.data.local.dataStore.AuthStorage
import com.example.cineverse.domain.model.LoginResponse
import com.example.cineverse.domain.repository.AuthRepository
import com.example.cineverse.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class LoginViewModel @Inject constructor(
    private val client: HttpClient,
    private val authStorage: AuthStorage,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authUiState = MutableStateFlow(LoginUiState())
    val authUiState: StateFlow<LoginUiState> = _authUiState

    private val _loginResponse = MutableStateFlow<Result<LoginResponse>>(Result.Empty)
    val loginResponse: StateFlow<Result<LoginResponse>> = _loginResponse


    init {
        viewModelScope.launch {
            authStorage.authDataFlow.collect { (token, loginState) ->
                _authUiState.update {
                    it.copy(
                        accessToken = token,
                        isLoggedIn = loginState ?: false
                    )
                }
            }
        }
    }

    private suspend fun getTokenProcess(): String? {
        Log.d("Ktor", "Starting fetch request token...")

        val requestTokenResponse =
            withContext(Dispatchers.IO) { authRepository.fetchRequestToken(client) }

        if (requestTokenResponse is Result.Success) {
            authStorage.saveAccessToken(
                accessToken = requestTokenResponse.data.requestToken,
                tokenExpiryDay = requestTokenResponse.data.expiresAt
            )
            _authUiState.update {
                it.copy(
                    accessToken = requestTokenResponse.data.requestToken,
                )
            }
            Log.d("Ktor", "Request token saved successfully")
            return requestTokenResponse.data.requestToken
        } else {
            // Log the specific error
            if (requestTokenResponse is Result.Error) {
                Log.e("LoginViewModel", "Failed to fetch token: ${requestTokenResponse.message}")
            }
            return null
        }
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
                _loginResponse.value = Result.Error.UnknownError("Failed to fetch request token. Please try again.")
            }

            if (_loginResponse.value is Result.Success) {
                authStorage.saveLoginState(true)
                Log.d("CineverseDataStore", "Login state saved successfully")
                _authUiState.update { it.copy(isLoggedIn = true) }

                // Get the session ID
                val sessionId = authRepository.getSessionId(
                    client = client,
                    requestToken = _authUiState.value.accessToken.toString()
                )

                if (sessionId is Result.Success) {
                    _authUiState.update {
                        it.copy(sessionId = sessionId.data.sessionId)
                    }
                    authStorage.saveSessionData(sessionId = sessionId.data.sessionId)
                    Log.d("CineverseDataStore", "Session ID saved successfully")
                }
            }
            
            if (_loginResponse.value is Result.Error) {
                authStorage.saveLoginState(false)
                val error = _loginResponse.value as Result.Error
                Log.e("LoginViewModel", "Login failed: ${error.message}")
                _authUiState.update { it.copy(isLoggedIn = false) }
            }
        }
    }

    fun joinAsGuest() {
        viewModelScope.launch(Dispatchers.IO) {
            val guestSessionResponse = authRepository.joinAsGuest(client)
            Log.d("Ktor", "Guest Session Response: $guestSessionResponse")

            if (guestSessionResponse is Result.Success) {
                val guestSessionId = guestSessionResponse.data.guestSessionId
                _authUiState.update {
                    it.copy(sessionId = guestSessionId)
                }
                authStorage.saveSessionData(sessionId = guestSessionId)
                Log.d("CineverseDataStore", "Guest session created successfully")
            } else if (guestSessionResponse is Result.Error) {
                Log.e("LoginViewModel", "Failed to create guest session: ${guestSessionResponse.message}")
            }
        }
    }

    fun onShowResetPSBottomSheet() {
        _authUiState.update {
            it.copy(showResetPSBottomSheet = true)
        }
    }

    fun onShowSignUpBottomSheet() {
        _authUiState.update {
            it.copy(showSignUpBottomSheet = true)
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
            it.copy(username = username)
        }
    }

    fun onPasswordChanged(password: String) {
        _authUiState.update {
            it.copy(password = password)
        }
    }
}