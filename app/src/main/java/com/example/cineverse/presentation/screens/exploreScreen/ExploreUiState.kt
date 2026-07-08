package com.example.cineverse.presentation.screens.exploreScreen

import com.example.cineverse.domain.model.Genre
import com.example.cineverse.domain.model.Movie

data class ExploreUiState(
    val searchQuery: String = "",
    val selectedTab: ExploreTab = ExploreTab.MOVIES,
    val selectedGenreId: Int? = 0,
    val viewMode: ViewMode = ViewMode.GRID,
    val movies: List<Movie> = emptyList(),
    val filteredMovies: List<Movie> = emptyList(),
    val searchedMovies: List<Movie> = emptyList(),
    val genres: List<Genre> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

enum class ExploreTab {
    MOVIES, SERIES
}

enum class ViewMode {
    GRID, LIST
}
