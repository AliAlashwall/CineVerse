package com.example.cineverse.presentation.screens.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineverse.data.local.dataStore.AuthStorage
import com.example.cineverse.domain.repository.MoviesRepository
import com.example.cineverse.domain.util.Result
import com.example.cineverse.domain.util.getNowPlayingMovies
import com.example.cineverse.domain.util.getPopularMovies
import com.example.cineverse.domain.util.getTopRatedMovies
import com.example.cineverse.domain.util.getUpComingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HomeEvent {
    object Loading : HomeEvent()
    object Success : HomeEvent()
    data class Error(val errorMessage: String, val errorType: ErrorType = ErrorType.UNKNOWN) : HomeEvent()
}

enum class ErrorType {
    NETWORK, TIMEOUT, SERVER, PARSING, UNKNOWN
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val client: HttpClient,
    private val moviesRepository: MoviesRepository,
    private val authStorage: AuthStorage
) : ViewModel() {
    private val _homeEvent = MutableStateFlow<HomeEvent>(HomeEvent.Loading)
    val homeEvent = _homeEvent.asStateFlow()

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        loadMovies()
        viewModelScope.launch {
            _homeUiState.update {
                it.copy(
                    guestId = authStorage.getSessionId()
                )
            }
        }
    }

    fun loadMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeEvent.value = HomeEvent.Loading
            try {
                coroutineScope {
                    val upcomingDeferred = async { moviesRepository.getUpComingMovies(client) }
                    val topRatedDeferred = async { moviesRepository.getTopRatedMovies(client) }
                    val popularDeferred = async { moviesRepository.getPopularMovies(client) }
                    val nowPlayingDeferred = async { moviesRepository.getNowPlayingMovies(client) }

                    val upcomingResult = upcomingDeferred.await()
                    val topRatedResult = topRatedDeferred.await()
                    val popularResult = popularDeferred.await()
                    val nowPlayingResult = nowPlayingDeferred.await()

                    val upcoming = upcomingResult.getUpComingMovies() ?: emptyList()
                    val topRated = topRatedResult.getTopRatedMovies() ?: emptyList()
                    val popular = popularResult.getPopularMovies() ?: emptyList()
                    val nowPlaying = nowPlayingResult.getNowPlayingMovies() ?: emptyList()

                    _homeUiState.update {
                        it.copy(
                            upcomingMovies = upcoming,
                            topRatedMovies = topRated,
                            popularMovies = popular,
                            nowPlayingMovies = nowPlaying
                        )
                    }

                    // Check if all requests succeeded
                    if (upcomingResult is Result.Success && 
                        topRatedResult is Result.Success && 
                        popularResult is Result.Success && 
                        nowPlayingResult is Result.Success) {
                        _homeEvent.value = HomeEvent.Success
                    } else {
                        // Get the first error encountered
                        val error = when {
                            upcomingResult is Result.Error -> upcomingResult
                            topRatedResult is Result.Error -> topRatedResult
                            popularResult is Result.Error -> popularResult
                            nowPlayingResult is Result.Error -> nowPlayingResult
                            else -> null
                        }

                        if (error != null) {
                            _homeEvent.value = mapErrorToEvent(error)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading movies", e)
                _homeEvent.value = HomeEvent.Error("An unexpected error occurred. Please try again.")
            }
        }
    }

    private fun mapErrorToEvent(error: Result.Error): HomeEvent.Error {
        return when (error) {
            is Result.Error.NetworkError -> {
                HomeEvent.Error(error.message, ErrorType.NETWORK)
            }
            is Result.Error.TimeoutError -> {
                HomeEvent.Error(error.message, ErrorType.TIMEOUT)
            }
            is Result.Error.ServerError -> {
                HomeEvent.Error(error.message, ErrorType.SERVER)
            }
            is Result.Error.ParsingError -> {
                HomeEvent.Error(error.message, ErrorType.PARSING)
            }
            is Result.Error.UnknownError -> {
                HomeEvent.Error(error.message, ErrorType.UNKNOWN)
            }
        }
    }
}