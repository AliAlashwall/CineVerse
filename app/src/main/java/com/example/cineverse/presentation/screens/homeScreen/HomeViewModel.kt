package com.example.cineverse.presentation.screens.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineverse.data.local.dataStore.AuthStorage
import com.example.cineverse.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.cineverse.domain.util.Result
import com.example.cineverse.domain.model.Movie
import javax.inject.Inject

sealed class HomeEvent {
    object Loading : HomeEvent()
    object Success : HomeEvent()
    object Error : HomeEvent()
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

    private fun loadMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeEvent.value = HomeEvent.Loading
            try {
                coroutineScope {
                    val upcomingDeferred =
                        async { getMovies { moviesRepository.getUpComingMovies(client) } }
                    val topRatedDeferred =
                        async { getMovies { moviesRepository.getTopRatedMovies(client) } }
                    val popularDeferred =
                        async { getMovies { moviesRepository.getPopularMovies(client) } }
                    val nowPlayingDeferred =
                        async { getMovies { moviesRepository.getNowPlayingMovies(client) } }

                    val upcoming = upcomingDeferred.await()
                    val topRated = topRatedDeferred.await()
                    val popular = popularDeferred.await()
                    val nowPlaying = nowPlayingDeferred.await()

                    _homeUiState.update {
                        it.copy(
                            upcomingMovies = upcoming ?: emptyList(),
                            topRatedMovies = topRated ?: emptyList(),
                            popularMovies = popular ?: emptyList(),
                            nowPlayingMovies = nowPlaying ?: emptyList()
                        )
                    }

                    // Check if all fetches succeeded (non-null means success)
                    if (upcoming != null && topRated != null && popular != null && nowPlaying != null) {
                        _homeEvent.value = HomeEvent.Success
                    } else {
                        _homeEvent.value = HomeEvent.Error
                    }
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading movies", e)
                _homeEvent.value = HomeEvent.Error
            }
        }
    }

    private suspend fun getMovies(fetch: suspend () -> Result<*>): List<Movie>? {
        return when (val result = fetch()) {
            is Result.Success -> {
                val data = result.data
                try {
                    val field = data!!::class.java.getDeclaredField("resultedMovies")
                    field.isAccessible = true
                    field.get(data) as? List<Movie>
                } catch (e: Exception) {
                    Log.e("HomeViewModel", "Error fetching movies", e)
                    null
                }
            }

            is Result.Error -> {
                Log.e("HomeViewModel", "Error fetching movies: ${result.message}")
                null
            }

            Result.Loading -> null
            Result.Empty -> emptyList()
        }
    }
}