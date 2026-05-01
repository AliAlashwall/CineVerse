package com.example.cineverse.presentation.screens.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineverse.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val client: HttpClient,
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    fun onStart (){
        viewModelScope.launch {
            Log.d(
                "HomeViewModel",
                "UpComing Movies: ${moviesRepository.getUpComingMovies(client = client)}"
            )
            delay(2000)

            Log.d(
                "HomeViewModel",
                "Top Rated Movies: ${moviesRepository.getTopRatedMovies(client = client)}"
            )
            delay(2000)

            Log.d(
                "HomeViewModel",
                "Now Playing Movies: ${moviesRepository.getNowPlayingMovies(client = client)}"
            )
            delay(2000)

            Log.d(
                "HomeViewModel",
                "Popular Movies: ${moviesRepository.getPopularMovies(client = client)}"
            )
        }
    }

}