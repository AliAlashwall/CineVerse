package com.example.cineverse.data.remote.repository

import com.example.cineverse.data.remote.dto.nowPlayingDto.NowPlayingMoviesDTO
import com.example.cineverse.data.remote.dto.popular.PopularMoviesDTO
import com.example.cineverse.data.remote.dto.topRated.TopRatedMoviesDto
import com.example.cineverse.data.remote.dto.upComingDto.UpComingResponseDTO
import com.example.cineverse.data.remote.mapper.toDomain
import com.example.cineverse.domain.model.NowPlayingMovies
import com.example.cineverse.domain.model.PopularMovies
import com.example.cineverse.domain.model.TopRatedMovies
import com.example.cineverse.domain.model.UpComingMovies
import com.example.cineverse.domain.repository.MoviesRepository
import com.example.cineverse.presentation.screens.loginScreen.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor() : MoviesRepository {

    override suspend fun getUpComingMovies(client: HttpClient): Result<UpComingMovies> {
        return try {
            val upComingMovies = client.get("movie/upcoming").body<UpComingResponseDTO>()
            Result.Success(data = upComingMovies.toDomain())
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "An error occurred while fetching upcoming movies")
        }
    }

    override suspend fun getTopRatedMovies(client: HttpClient): Result<TopRatedMovies> {
        return try {
            val topRatedMovies = client.get("movie/top_rated").body<TopRatedMoviesDto>()
            Result.Success(data = topRatedMovies.toDomain())
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "An error occurred while fetching top rated movies")
        }
    }

    override suspend fun getNowPlayingMovies(client: HttpClient): Result<NowPlayingMovies> {
        return try {
            val nowPlayingMovies = client.get("movie/now_playing").body<NowPlayingMoviesDTO>()
            Result.Success(data = nowPlayingMovies.toDomain())
        } catch (e: Exception) {
            Result.Error(
                e.localizedMessage ?: "An error occurred while fetching now playing movies"
            )
        }

    }

    override suspend fun getPopularMovies(client: HttpClient): Result<PopularMovies> {
        return try {
            val popularMovies = client.get("movie/popular").body<PopularMoviesDTO>()
            Result.Success(data = popularMovies.toDomain())
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "An error occurred while fetching popular movies")
        }
    }
}