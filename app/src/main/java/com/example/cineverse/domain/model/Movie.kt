package com.example.cineverse.domain.model

data class Movie(
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val softcore: Boolean,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

val mockMoviesList: List<Movie> = listOf(
    Movie(
        adult = false,
        backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
        genreIds = listOf(
            18,
            80
        ),
        id = 2787,
        title = "The Shawshank Redemption",
        originalLanguage = "en",
        originalTitle = "The Shawshank Redemption",
        overview = "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
        popularity = 48.5295,
        posterPath = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
        releaseDate = "1994-09-23",
        softcore = false,
        video = false,
        voteAverage = 7.5,
        voteCount = 30212
    ),
    Movie(
        adult = false,
        backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
        genreIds = listOf(
            18,
            80
        ),
        id = 2758,
        title = "The Shawshank Redemption",
        originalLanguage = "en",
        originalTitle = "The Shawshank Redemption",
        overview = "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
        popularity = 48.5295,
        posterPath = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
        releaseDate = "1994-09-23",
        softcore = false,
        video = false,
        voteAverage = 7.5,
        voteCount = 30212
    ),
    Movie(
        adult = false,
        backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
        genreIds = listOf(
            18,
            80
        ),
        id = 2768,
        title = "The Shawshank Redemption",
        originalLanguage = "en",
        originalTitle = "The Shawshank Redemption",
        overview = "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
        popularity = 48.5295,
        posterPath = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
        releaseDate = "1994-09-23",
        softcore = false,
        video = false,
        voteAverage = 7.5,
        voteCount = 30212
    ),
    Movie(
        adult = false,
        backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
        genreIds = listOf(
            18,
            80
        ),
        id = 2728,
        title = "The Shawshank Redemption",
        originalLanguage = "en",
        originalTitle = "The Shawshank Redemption",
        overview = "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
        popularity = 48.5295,
        posterPath = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
        releaseDate = "1994-09-23",
        softcore = false,
        video = false,
        voteAverage = 7.5,
        voteCount = 30212
    ),
    Movie(
        adult = false,
        backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
        genreIds = listOf(
            18,
            80
        ),
        id = 1278,
        title = "The Shawshank Redemption",
        originalLanguage = "en",
        originalTitle = "The Shawshank Redemption",
        overview = "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
        popularity = 48.5295,
        posterPath = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
        releaseDate = "1994-09-23",
        softcore = false,
        video = false,
        voteAverage = 7.5,
        voteCount = 30212
    ), Movie(
        adult = false,
        backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
        genreIds = listOf(
            18,
            80
        ),
        id = 25478,
        title = "The Shawshank Redemption",
        originalLanguage = "en",
        originalTitle = "The Shawshank Redemption",
        overview = "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
        popularity = 48.5295,
        posterPath = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
        releaseDate = "1994-09-23",
        softcore = false,
        video = false,
        voteAverage = 7.5,
        voteCount = 30212
    ), Movie(
        adult = false,
        backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
        genreIds = listOf(
            18,
            80
        ),
        id = 27128,
        title = "The Shawshank Redemption",
        originalLanguage = "en",
        originalTitle = "The Shawshank Redemption",
        overview = "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
        popularity = 48.5295,
        posterPath = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
        releaseDate = "1994-09-23",
        softcore = false,
        video = false,
        voteAverage = 7.5,
        voteCount = 30212
    ),
    Movie(
        adult = false,
        backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
        genreIds = listOf(
            18,
            80
        ),
        id = 27438,
        title = "The Shawshank Redemption",
        originalLanguage = "en",
        originalTitle = "The Shawshank Redemption",
        overview = "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
        popularity = 48.5295,
        posterPath = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
        releaseDate = "1994-09-23",
        softcore = false,
        video = false,
        voteAverage = 7.5,
        voteCount = 30212
    ),
    Movie(
        adult = false,
        backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
        genreIds = listOf(
            18,
            80
        ),
        id = 27658,
        title = "The Shawshank Redemption",
        originalLanguage = "en",
        originalTitle = "The Shawshank Redemption",
        overview = "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
        popularity = 48.5295,
        posterPath = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
        releaseDate = "1994-09-23",
        softcore = false,
        video = false,
        voteAverage = 7.5,
        voteCount = 30212
    ),
    Movie(
        adult = false,
        backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
        genreIds = listOf(
            18,
            80
        ),
        id = 27668,
        title = "The Shawshank Redemption",
        originalLanguage = "en",
        originalTitle = "The Shawshank Redemption",
        overview = "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
        popularity = 48.5295,
        posterPath = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
        releaseDate = "1994-09-23",
        softcore = false,
        video = false,
        voteAverage = 7.5,
        voteCount = 30212
    )
)