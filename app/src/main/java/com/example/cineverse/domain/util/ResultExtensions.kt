package com.example.cineverse.domain.util

import com.example.cineverse.domain.model.Movie
import com.example.cineverse.domain.model.NowPlayingMovies
import com.example.cineverse.domain.model.PopularMovies
import com.example.cineverse.domain.model.TopRatedMovies
import com.example.cineverse.domain.model.UpComingMovies

fun Result<UpComingMovies>.getUpComingMovies(): List<Movie>? = when (this) {
    is Result.Success -> data.resultedMovies
    else -> null
}

fun Result<TopRatedMovies>.getTopRatedMovies(): List<Movie>? = when (this) {
    is Result.Success -> data.resultedMovies
    else -> null
}

fun Result<PopularMovies>.getPopularMovies(): List<Movie>? = when (this) {
    is Result.Success -> data.resultedMovies
    else -> null
}

fun Result<NowPlayingMovies>.getNowPlayingMovies(): List<Movie>? = when (this) {
    is Result.Success -> data.resultedMovies
    else -> null
}
