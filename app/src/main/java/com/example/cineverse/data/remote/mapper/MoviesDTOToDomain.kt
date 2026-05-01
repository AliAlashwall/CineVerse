package com.example.cineverse.data.remote.mapper

import com.example.cineverse.data.remote.dto.DatesDto
import com.example.cineverse.data.remote.dto.ResultedMovieDto
import com.example.cineverse.data.remote.dto.movieDetails.BelongsToCollectionDTO
import com.example.cineverse.data.remote.dto.movieDetails.CastDTO
import com.example.cineverse.data.remote.dto.movieDetails.CreditsDTO
import com.example.cineverse.data.remote.dto.movieDetails.CrewDTO
import com.example.cineverse.data.remote.dto.movieDetails.GenreDTO
import com.example.cineverse.data.remote.dto.movieDetails.MovieDetailsDTO
import com.example.cineverse.data.remote.dto.movieDetails.ProductionCompanyDTO
import com.example.cineverse.data.remote.dto.movieDetails.ProductionCountryDTO
import com.example.cineverse.data.remote.dto.movieDetails.SpokenLanguageDTO
import com.example.cineverse.data.remote.dto.nowPlayingDto.NowPlayingMoviesDTO
import com.example.cineverse.data.remote.dto.popular.PopularMoviesDTO
import com.example.cineverse.data.remote.dto.topRated.TopRatedMoviesDto
import com.example.cineverse.data.remote.dto.upComingDto.UpComingResponseDTO
import com.example.cineverse.domain.model.BelongsToCollection
import com.example.cineverse.domain.model.Cast
import com.example.cineverse.domain.model.Credits
import com.example.cineverse.domain.model.Crew
import com.example.cineverse.domain.model.Genre
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.domain.model.MovieDates
import com.example.cineverse.domain.model.MovieDetails
import com.example.cineverse.domain.model.NowPlayingMovies
import com.example.cineverse.domain.model.PopularMovies
import com.example.cineverse.domain.model.ProductionCompany
import com.example.cineverse.domain.model.ProductionCountry
import com.example.cineverse.domain.model.SpokenLanguage
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

fun BelongsToCollectionDTO.toDomain(): BelongsToCollection {
    return BelongsToCollection(
        backdropPath = this.backdropPath,
        id = this.id,
        name = this.name,
        posterPath = this.posterPath
    )
}

fun CastDTO.toDomain(): Cast {
    return Cast(
        adult = this.adult,
        castId = this.castId,
        character = this.character,
        creditId = this.creditId,
        gender = this.gender,
        id = this.id,
        knownForDepartment = this.knownForDepartment,
        name = this.name,
        order = this.order,
        originalName = this.originalName,
        popularity = this.popularity,
        profilePath = this.profilePath
    )
}

fun CrewDTO.toDomain(): Crew {
    return Crew(
        adult = this.adult,
        creditId = this.creditId,
        department = this.department,
        gender = this.gender,
        id = this.id,
        job = this.job,
        knownForDepartment = this.knownForDepartment,
        name = this.name,
        originalName = this.originalName,
        popularity = this.popularity,
        profilePath = this.profilePath
    )
}

fun CreditsDTO.toDomain(): Credits {
    val cast = this.cast.map { it.toDomain() }
    val crew = this.crew.map { it.toDomain() }
    return Credits(
        cast = cast,
        crew = crew
    )
}

fun GenreDTO.toDomain(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}

fun ProductionCompanyDTO.toDomain(): ProductionCompany {
    return ProductionCompany(
        id = this.id,
        logoPath = this.logoPath,
        name = this.name,
        originCountry = this.originCountry
    )
}

fun ProductionCountryDTO.toDomain(): ProductionCountry {
    return ProductionCountry(
        iso31661 = this.iso31661,
        name = this.name
    )
}

fun SpokenLanguageDTO.toDomain(): SpokenLanguage {
    return SpokenLanguage(
        englishName = this.englishName,
        iso6391 = this.iso6391,
        name = this.name
    )
}

fun MovieDetailsDTO.toDomain(): MovieDetails {
    val belongsToCollection = this.belongsToCollection.toDomain()
    val credits = this.credits.toDomain()
    val genres = this.genres.map { it.toDomain() }
    val productionCompanies = this.productionCompanies.map { it.toDomain() }
    val productionCountries = this.productionCountries.map { it.toDomain() }
    val spokenLanguages = this.spokenLanguages.map { it.toDomain() }

    return MovieDetails(
        adult = this.adult,
        backdropPath = this.backdropPath,
        belongsToCollection = belongsToCollection,
        budget = this.budget,
        credits = credits,
        genres = genres,
        homepage = this.homepage,
        id = this.id,
        imdbId = this.imdbId,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = productionCompanies,
        productionCountries = productionCountries,
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        softcore = this.softcore,
        spokenLanguages = spokenLanguages,
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

