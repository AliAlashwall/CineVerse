package com.example.cineverse.data.remote.mapper

import com.example.cineverse.data.remote.dto.DatesDto
import com.example.cineverse.data.remote.dto.ResultedMovieDto
import com.example.cineverse.data.remote.dto.nowPlayingDto.NowPlayingMoviesDTO
import com.example.cineverse.data.remote.dto.popular.PopularMoviesDTO
import com.example.cineverse.data.remote.dto.topRated.TopRatedMoviesDto
import com.example.cineverse.data.remote.dto.upComingDto.UpComingResponseDTO
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.domain.model.MovieDates
import com.example.cineverse.domain.model.NowPlayingMovies
import com.example.cineverse.domain.model.PopularMovies
import com.example.cineverse.domain.model.TopRatedMovies
import com.example.cineverse.domain.model.UpComingMovies


fun ResultedMovieDto.toDomain(): Movie {
    return Movie(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        softcore = this.softcore,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun DatesDto.toDomain(): MovieDates {
    return MovieDates(
        maximum = this.maximum,
        minimum = this.minimum
    )
}

fun UpComingResponseDTO.toDomain(): UpComingMovies {
    val movies = this.resultedUpComingMovieDtos.map { it.toDomain() }
    val dates = this.dates.toDomain()

    return UpComingMovies(
        dates = dates,
        page = this.page,
        resultedMovies = movies,
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}

fun TopRatedMoviesDto.toDomain(): TopRatedMovies {
    val movies = this.results.map { it.toDomain() }
    return TopRatedMovies(
        page = this.page,
        resultedMovies = movies,
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}

fun PopularMoviesDTO.toDomain(): PopularMovies {
    val movies = this.resultedPopularMoviesDto.map { it.toDomain() }
    return PopularMovies(
        page = this.page,
        totalPages = this.totalPages,
        totalResults = this.totalResults,
        resultedMovies = movies
    )
}

fun NowPlayingMoviesDTO.toDomain(): NowPlayingMovies {
    val movies = this.resultedNowPlayingMovieDtos.map { it.toDomain() }
    val dates = this.dates.toDomain()
    return NowPlayingMovies(
        dates = dates,
        page = this.page,
        resultedMovies = movies,
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}