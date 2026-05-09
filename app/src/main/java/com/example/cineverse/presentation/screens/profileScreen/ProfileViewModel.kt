package com.example.cineverse.presentation.screens.profileScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineverse.data.local.dataStore.AuthStorage
import com.example.cineverse.domain.repository.AccountRepository
import com.example.cineverse.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val client: HttpClient,
    private val authStorage: AuthStorage,
    private val accountRepository: AccountRepository
) : ViewModel() {
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState = _profileUiState.asStateFlow()


    init {
        getAccountData()
    }

    fun getAccountData() {
        viewModelScope.launch {
            _profileUiState.update {
                it.copy(
                    sessionId = authStorage.getSessionId() ?: ""
                )
            }

            val accountResponse = accountRepository.getAccountDetails(
                client = client,
                sessionId = _profileUiState.value.sessionId
            )
            if (accountResponse is Result.Success) {
                _profileUiState.update {
                    it.copy(
                        userName = accountResponse.data.username,
                        name = accountResponse.data.name,
                        gravatar = accountResponse.data.avatar.gravatar.hash,
                        avatarPath = accountResponse.data.avatar.tmdb.avatarPath
                    )
                }
            }
        }
    }
}