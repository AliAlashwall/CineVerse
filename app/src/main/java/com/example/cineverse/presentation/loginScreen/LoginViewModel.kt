package com.example.cineverse.presentation.loginScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineverse.data.local.dataStore.TokenStorage
import com.example.cineverse.domain.model.RequestTokenResponseDTO
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


sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class LoginViewModel @Inject constructor(
    private val client: HttpClient,
    private val tokenStorage: TokenStorage,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _tokenResponse =
        MutableStateFlow<UiState<RequestTokenResponseDTO>>(UiState.Loading)
    val tokenResponse: StateFlow<UiState<RequestTokenResponseDTO>> = _tokenResponse


    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    init {
        //To avoid getting new access token every time we create this view model
        viewModelScope.launch {
            val savedAccessToken = tokenStorage.getAccessToken()
            if (savedAccessToken != null) {
                _loginUiState.update {
                    it.copy(
                        accessToken = savedAccessToken,
                    )
                }
            }
        }
    }

    suspend fun getTokenProcess(): String? {

        Log.d("Ktor", "Starting fetch...")
        return try {
            val result =
                withContext(Dispatchers.IO) { authRepository.fetchRequestToken(client) }

            // Save the tokens to storage!
            if (result.success) {
                tokenStorage.saveAccessToken(
                    accessToken = result.requestToken,
                    tokenExpiryDay = result.expiresAt
                )
                _loginUiState.update {
                    it.copy(
                        accessToken = result.requestToken,
                    )
                }
            }
            Log.d("Ktor", "Access Token= ${tokenStorage.getAccessToken()}")

            _tokenResponse.value = UiState.Success(result)
            result.requestToken
        } catch (e: Exception) {
            Log.e("Ktor", "Error", e)
            _tokenResponse.value = UiState.Error(e.localizedMessage ?: "Unknown error")
            return null
        }

    }

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            val accessToken = getTokenProcess()
            if (accessToken != null) {
                try {
                    val loginResponse =
                        authRepository.login(
                            client = client,
                            username = _loginUiState.value.username,
                            password = _loginUiState.value.password,
                            accessToken
                        )
                    if (loginResponse.success) {
                        tokenStorage.saveSessionData(sessionExpiryDay = loginResponse.expiresAt)
                        Log.d("Ktor", "Login Response: $loginResponse")
                    }

                } catch (e: Exception) {
                    Log.e("Ktor", "Error during login", e)
                }
            }
        }
    }

    fun joinAsGuest() {
        //TODO
    }

    fun signUp() {
        //TODO
    }

    fun resetPassword() {
        //TODO
    }

    fun onShowResetPSBottomSheet() {
        _loginUiState.update {
            it.copy(
                showResetPSBottomSheet = true
            )
        }
    }

    fun onShowSignUpBottomSheet() {
        _loginUiState.update {
            it.copy(
                showSignUpBottomSheet = true
            )
        }
    }

    fun onDismissBottomSheet() {
        _loginUiState.update {
            it.copy(
                showResetPSBottomSheet = false,
                showSignUpBottomSheet = false,
            )
        }
    }

    fun onUsernameChanged(username: String) {
        _loginUiState.update {
            it.copy(
                username = username
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _loginUiState.update {
            it.copy(
                password = password
            )
        }

    }
}