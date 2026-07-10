package com.example.cineverse.presentation.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.cineverse.data.local.dataStore.AuthStorage
import com.example.cineverse.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    moviesRepository: MoviesRepository,
    private val authStorage: AuthStorage
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        viewModelScope.launch {
            _homeUiState.update {
                it.copy(
                    guestId = authStorage.getSessionId()
                )
            }
        }
    }

    val upcomingMovies = moviesRepository.getUpComingMovies().cachedIn(viewModelScope)
    val popularMovies = moviesRepository.getPopularMovies().cachedIn(viewModelScope)
    val topRatedMovies = moviesRepository.getTopRatedMovies().cachedIn(viewModelScope)
    val nowPlayingMovies = moviesRepository.getNowPlayingMovies().cachedIn(viewModelScope)

}