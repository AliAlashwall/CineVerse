package com.example.cineverse.data.remote.repository

import android.util.Log
import com.example.cineverse.data.remote.dto.GenresListDTO
import com.example.cineverse.data.remote.dto.movieDetails.MovieDetailsDTO
import com.example.cineverse.data.remote.dto.nowPlayingDto.NowPlayingMoviesDTO
import com.example.cineverse.data.remote.dto.popular.PopularMoviesDTO
import com.example.cineverse.data.remote.dto.topRated.TopRatedMoviesDto
import com.example.cineverse.data.remote.dto.upComingDto.UpComingResponseDTO
import com.example.cineverse.data.remote.mapper.toDomain
import com.example.cineverse.data.remote.util.HttpErrorHandler
import com.example.cineverse.domain.model.GenresList
import com.example.cineverse.domain.model.MovieDetails
import com.example.cineverse.domain.model.NowPlayingMovies
import com.example.cineverse.domain.model.PopularMovies
import com.example.cineverse.domain.model.TopRatedMovies
import com.example.cineverse.domain.model.UpComingMovies
import com.example.cineverse.domain.repository.MoviesRepository
import com.example.cineverse.domain.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor() : MoviesRepository {

    override suspend fun getUpComingMovies(client: HttpClient): Result<UpComingMovies> {
        return try {
            val upComingMovies = client.get("movie/upcoming") {
                parameter("page", 5)
            }.body<UpComingResponseDTO>()
            Result.Success(data = upComingMovies.toDomain())
        } catch (e: Exception) {
            HttpErrorHandler.handleException(e, "Failed to fetch upcoming movies")
        }
    }

    override suspend fun getTopRatedMovies(client: HttpClient): Result<TopRatedMovies> {
        return try {
            val topRatedMovies = client.get("movie/top_rated").body<TopRatedMoviesDto>()
            Result.Success(data = topRatedMovies.toDomain())
        } catch (e: Exception) {
            HttpErrorHandler.handleException(e, "Failed to fetch top rated movies")
        }
    }

    override suspend fun getNowPlayingMovies(client: HttpClient): Result<NowPlayingMovies> {
        return try {
            val nowPlayingMovies = client.get("movie/now_playing").body<NowPlayingMoviesDTO>()
            Result.Success(data = nowPlayingMovies.toDomain())
        } catch (e: Exception) {
            HttpErrorHandler.handleException(e, "Failed to fetch now playing movies")
        }
    }

    override suspend fun getPopularMovies(client: HttpClient): Result<PopularMovies> {
        return try {
            val popularMovies = client.get("movie/popular").body<PopularMoviesDTO>()
            Result.Success(data = popularMovies.toDomain())
        } catch (e: Exception) {
            HttpErrorHandler.handleException(e, "Failed to fetch popular movies")
        }
    }

    override suspend fun getMovieDetails(
        movieId: Int,
        client: HttpClient
    ): Result<MovieDetails> {
        return try {
            val movieDetails = client.get("movie/$movieId") {
                parameter("append_to_response", "credits,reviews")
            }.body<MovieDetailsDTO>()
            Log.d(
                "MoviesRepositoryImpl",
                "Fetched movie details successfully for movie ID: $movieId"
            )
            Result.Success(data = movieDetails.toDomain())
        } catch (e: Exception) {
            HttpErrorHandler.handleException(e, "Failed to fetch movie details")
        }
    }

    override suspend fun getGenreList(client: HttpClient): Result<GenresList> {
        return try {
            val listOfGenres = client.get("genre/movie/list").body<GenresListDTO>()
            Log.d("MoviesRepositoryImpl", "Fetched genre list successfully")
            Result.Success(data = listOfGenres.toDomain())
        } catch (e: Exception) {
            HttpErrorHandler.handleException(e, "Failed to fetch genre list")

        }

    }

    override suspend fun searchForMoviesByName(
        client: HttpClient,
        query: String
    ): Result<TopRatedMovies> {
        return try {
            val response = client.get("search/movie") {
                parameter("query", query)
            }.body<TopRatedMoviesDto>()
            Result.Success(response.toDomain())
        } catch (e: Exception) {
            Log.d("MoviesRepositoryImpl", "Error searching for movies by name: ${e.message}")
            HttpErrorHandler.handleException(e, "Failed to search for movies by name")
        }
    }
}
