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
        getPopularMovies()
    }

    fun getMovieDetails(moveId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movieDetails =
                    moviesRepository.getMovieDetails(movieId = moveId, client = client)
                Log.d("MovieDetailsViewModel", "Movie details: $movieDetails")
                if (movieDetails is Result.Success) {
                    _movieUiState.update { it.copy(movieDetails = movieDetails.data) }
                    Log.d("MovieDetailsViewModel", "Movie details: ${movieDetails.data}")
                }

            } catch (e: Exception) {
                Log.e("MovieDetailsViewModel", "Error fetching movie details", e)
            }

        }
    }

    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = moviesRepository.getPopularMovies(client)

            if (movies is Result.Success) {
                _movieUiState.update { it.copy(popularMovies = movies.data.resultedMovies) }
            }
            if (movies is Result.Error) {
                Log.e("MovieDetailsViewModel", "Error fetching popular movies: ${movies.message}")
            }

        }
    }
}