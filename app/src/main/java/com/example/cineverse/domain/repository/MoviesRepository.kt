package com.example.cineverse.domain.repository

import androidx.paging.PagingData
import com.example.cineverse.domain.model.GenresList
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.domain.model.MovieDetails
import com.example.cineverse.domain.model.NowPlayingMovies
import com.example.cineverse.domain.model.TopRatedMovies
import com.example.cineverse.domain.model.UpComingMovies
import com.example.cineverse.domain.util.Result
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getUpComingMovies(client: HttpClient): Result<UpComingMovies>

    suspend fun getTopRatedMovies(client: HttpClient): Result<TopRatedMovies>

    suspend fun getNowPlayingMovies(client: HttpClient): Result<NowPlayingMovies>

    fun getPopularMovies(client: HttpClient): Flow<PagingData<Movie>>

    suspend fun getMovieDetails(movieId: Int, client: HttpClient): Result<MovieDetails>

    suspend fun getGenreList(client: HttpClient): Result<GenresList>

    suspend fun searchForMoviesByName(client: HttpClient, query: String): Flow<PagingData<Movie>>


}