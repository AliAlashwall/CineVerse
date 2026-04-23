package com.example.cineverse.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineverse.domain.model.RequestTokenResponseDTO
import com.example.cineverse.domain.repository.Repository
import com.example.cineverse.util.TokenStorage
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
class KtorViewModel @Inject constructor(
    private val client: HttpClient,
    private val tokenStorage: TokenStorage,
    private val repository: Repository
) : ViewModel() {

    private val _tokenResponse =
        MutableStateFlow<UiState<RequestTokenResponseDTO>>(UiState.Loading)
    val tokenResponse: StateFlow<UiState<RequestTokenResponseDTO>> = _tokenResponse


    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState: StateFlow<AuthUiState> = _authUiState

    fun getTokenProcess() {

        viewModelScope.launch {
            Log.d("Ktor", "Starting fetch...")
            try {
                val result = withContext(Dispatchers.IO) { repository.fetchRequestToken(client) }

                // Save the tokens to storage!
                if (result.success) {
                    tokenStorage.saveAccessToken(
                        accessToken = result.requestToken,
                        expiryDay = result.expiresAt
                    )
                    _authUiState.update {
                        it.copy(
                            accessToken = result.requestToken,
                            expiryDay = result.expiresAt
                        )
                    }
                }
                Log.d("Ktor", "Access Token= ${tokenStorage.getAccessToken()}")

                _tokenResponse.value = UiState.Success(result)
            } catch (e: Exception) {
                Log.e("Ktor", "Error", e)
                _tokenResponse.value = UiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}