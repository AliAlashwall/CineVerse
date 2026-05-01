package com.example.cineverse.presentation.screens.movieDetailsScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineverse.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val client: HttpClient,
    private val moviesRepository: MoviesRepository
) : ViewModel() {



    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieDetails = moviesRepository.getMovieDetails(movieId = movieId, client = client)
            Log.d("MovieDetailsViewModel", "Movie details: $movieDetails")
        }
    }
}