package com.example.cineverse.domain.repository

import com.example.cineverse.domain.model.MovieDetails
import com.example.cineverse.domain.model.NowPlayingMovies
import com.example.cineverse.domain.model.PopularMovies
import com.example.cineverse.domain.model.TopRatedMovies
import com.example.cineverse.domain.model.UpComingMovies
import com.example.cineverse.domain.util.Result
import io.ktor.client.HttpClient

interface MoviesRepository {
    suspend fun getUpComingMovies(client: HttpClient): Result<UpComingMovies>

    suspend fun getTopRatedMovies(client: HttpClient): Result<TopRatedMovies>

    suspend fun getNowPlayingMovies(client: HttpClient): Result<NowPlayingMovies>

    suspend fun getPopularMovies(client: HttpClient): Result<PopularMovies>

    suspend fun getMovieDetails(movieId: Int, client: HttpClient): Result<MovieDetails>

}