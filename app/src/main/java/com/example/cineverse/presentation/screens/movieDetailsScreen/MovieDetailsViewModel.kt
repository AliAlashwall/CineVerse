package com.example.cineverse.presentation.screens.movieDetailsScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineverse.domain.repository.MoviesRepository
import com.example.cineverse.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val client: HttpClient,
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val _movieUiState = MutableStateFlow(MovieUiState())
    val movieUiState = _movieUiState.asStateFlow()

    init {
        getTopRatedMovies()
    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _movieUiState.update { it.copy(isLoadingDetails = true, detailsError = null) }
            
            val movieDetails = moviesRepository.getMovieDetails(movieId = movieId, client = client)
            
            when (movieDetails) {
                is Result.Success -> {
                    _movieUiState.update { 
                        it.copy(
                            movieDetails = movieDetails.data,
                            isLoadingDetails = false
                        ) 
                    }
                    Log.d("MovieDetailsViewModel", "Movie details loaded successfully for movie ID: $movieId")
                }
                is Result.Error -> {
                    _movieUiState.update { 
                        it.copy(
                            isLoadingDetails = false,
                            detailsError = movieDetails.message
                        ) 
                    }
                    Log.e("MovieDetailsViewModel", "Failed to fetch movie details: ${movieDetails.message}")
                }
                else -> {
                    _movieUiState.update { it.copy(isLoadingDetails = false) }
                }
            }
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _movieUiState.update { it.copy(isLoading = true, error = null) }

            when (val movies = moviesRepository.getTopRatedMovies(client)) {
                is Result.Success -> {
                    _movieUiState.update { 
                        it.copy(
                            topRatedMovies = movies.data.resultedMovies,
                            isLoading = false
                        ) 
                    }
                    Log.d("MovieDetailsViewModel", "TopRated movies loaded successfully")
                }
                is Result.Error -> {
                    _movieUiState.update { 
                        it.copy(
                            isLoading = false,
                            error = movies.message
                        ) 
                    }
                    Log.e("MovieDetailsViewModel", "Failed to fetch TopRated movies: ${movies.message}")
                }
                else -> {
                    _movieUiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }
}