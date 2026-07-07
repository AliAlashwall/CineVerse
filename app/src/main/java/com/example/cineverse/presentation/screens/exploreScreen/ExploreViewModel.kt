package com.example.cineverse.presentation.screens.exploreScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineverse.domain.repository.MoviesRepository
import com.example.cineverse.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val client: HttpClient,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getMovies()
        getGenres()
    }

    private fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = moviesRepository.getPopularMovies(client)
            if (movies is Result.Success) {
                val allMovies = movies.data.resultedMovies
                _uiState.update {
                    it.copy(
                        movies = allMovies,
                        filteredMovies = allMovies,
                        isLoading = false
                    )
                }
            }


        }
    }

    private fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            val genres = moviesRepository.getGenreList(client)
            if (genres is Result.Success) {
                _uiState.update {
                    it.copy(
                        genres = genres.data.genres,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onTabSelected(tab: ExploreTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }

    fun onGenreSelected(genreId: Int) {
        val filteredMovies = if (genreId == 0) {
            _uiState.value.movies
        } else {
            _uiState.value.movies.filter { it.genreIds.contains(genreId) }
        }
        _uiState.update {
            it.copy(
                selectedGenreId = genreId,
                filteredMovies = filteredMovies
            )
        }
    }

    fun onViewModeToggle() {
        _uiState.update {
            it.copy(
                viewMode = if (it.viewMode == ViewMode.GRID) ViewMode.LIST else ViewMode.GRID
            )
        }
    }
}
