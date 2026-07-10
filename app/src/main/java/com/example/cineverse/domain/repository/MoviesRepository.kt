package com.example.cineverse.domain.repository

import androidx.paging.PagingData
import com.example.cineverse.domain.model.GenresList
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.domain.model.MovieDetails
import com.example.cineverse.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
     fun getUpComingMovies(): Flow<PagingData<Movie>>

     fun getTopRatedMovies(): Flow<PagingData<Movie>>

     fun getNowPlayingMovies(): Flow<PagingData<Movie>>

    fun getPopularMovies(): Flow<PagingData<Movie>>

    suspend fun getMovieDetails(movieId: Int): Result<MovieDetails>

    suspend fun getGenreList(): Result<GenresList>

    suspend fun searchForMoviesByName(query: String): Flow<PagingData<Movie>>


}