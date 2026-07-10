package com.example.cineverse.data.remote.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.cineverse.data.remote.dto.GenresListDTO
import com.example.cineverse.data.remote.dto.movieDetails.MovieDetailsDTO
import com.example.cineverse.data.remote.dto.nowPlayingDto.NowPlayingMoviesDTO
import com.example.cineverse.data.remote.dto.popular.PopularMoviesDTO
import com.example.cineverse.data.remote.dto.topRated.TopRatedMoviesDto
import com.example.cineverse.data.remote.dto.upComingDto.UpComingResponseDTO
import com.example.cineverse.data.remote.mapper.toDomain
import com.example.cineverse.data.remote.util.HttpErrorHandler
import com.example.cineverse.domain.model.GenresList
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.domain.model.MovieDetails
import com.example.cineverse.domain.repository.MoviesRepository
import com.example.cineverse.domain.util.Result
import com.example.cineverse.presentation.paging.BasePagingSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : MoviesRepository {

    override fun getUpComingMovies(): Flow<PagingData<Movie>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                BasePagingSource { page ->
                    httpClient.get("movie/upcoming") {
                        parameter("page", page)
                    }.body<UpComingResponseDTO>().toDomain().resultedMovies
                }
            }
        ).flow

    }

    override fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                BasePagingSource { page ->
                    httpClient.get("movie/top_rated") {
                        parameter("page", page)
                    }.body<TopRatedMoviesDto>().toDomain().resultedMovies
                }
            }
        ).flow
    }

    override fun getNowPlayingMovies(): Flow<PagingData<Movie>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                BasePagingSource { page ->
                    httpClient.get("movie/now_playing") {
                        parameter("page", page)
                    }.body<NowPlayingMoviesDTO>()
                        .toDomain().resultedMovies
                }
            }
        ).flow
    }

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                BasePagingSource { page ->
                    httpClient.get("movie/popular") {
                        parameter("page", page)
                    }.body<PopularMoviesDTO>().toDomain().resultedMovies
                }
            }
        ).flow
    }

    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetails> {
        return try {
            val movieDetails = httpClient.get("movie/$movieId") {
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

    override suspend fun getGenreList(): Result<GenresList> {
        return try {
            val listOfGenres = httpClient.get("genre/movie/list").body<GenresListDTO>()
            Log.d("MoviesRepositoryImpl", "Fetched genre list successfully")
            Result.Success(data = listOfGenres.toDomain())
        } catch (e: Exception) {
            HttpErrorHandler.handleException(e, "Failed to fetch genre list")

        }

    }

    override suspend fun searchForMoviesByName(query: String): Flow<PagingData<Movie>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                BasePagingSource { page ->
                    httpClient.get("search/movie") {
                        parameter("query", query)
                        parameter("page", page)
                    }.body<TopRatedMoviesDto>().toDomain().resultedMovies
                }
            }
        ).flow
    }
}