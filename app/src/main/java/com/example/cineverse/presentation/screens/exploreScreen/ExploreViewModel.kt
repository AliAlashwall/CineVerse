package com.example.cineverse.presentation.screens.exploreScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.domain.repository.MoviesRepository
import com.example.cineverse.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
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
        getGenres()
    }


    val popularMovies = moviesRepository.getPopularMovies(client).cachedIn(viewModelScope)


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


    fun getMovieGenres(movie: Movie): String {
        val movieGenres = _uiState.value.genres.filter { it.id in movie.genreIds }
            .joinToString(", ") { it.name }
        return movieGenres
    }


    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val searchedMovies: Flow<PagingData<Movie>> = uiState
        .map { it.searchQuery }
        .debounce(500) // Wait for user to stop typing
        .distinctUntilChanged()
        .flatMapLatest { query ->
           moviesRepository.searchForMoviesByName(client,query)
        }
        .cachedIn(viewModelScope) // Crucial for Paging


    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        Log.d("ExploreViewModel", "Search query changed: $query")
    }

    fun onTabSelected(tab: ExploreTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }

    fun onGenreSelected(genreId: Int) {
        _uiState.update {
            it.copy(
                selectedGenreId = genreId,
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

    /*private fun observeSearchQuery() {
        _uiState
            .map { it.searchQuery }
            .debounce(500)
            .distinctUntilChanged()
            .filter { it.isNotBlank() }
            .launchIn(viewModelScope)
    }*/

    /*private fun searchMovies(query: String) = flow {
        _uiState.update { it.copy(isLoading = true) }

        when (val result = moviesRepository.searchForMoviesByName(client, query)) {
            is Result.Success -> {
                _uiState.update {
                    it.copy(
                        searchedMovies = result.data.resultedMovies,
                        isLoading = false,
                        error = null
                    )
                }
                Log.d(
                    "ExploreViewModel",
                    "Successfully searched for movies: ${_uiState.value.searchedMovies}"
                )
            }

            is Result.Error -> {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }

            else -> {}
        }
        emit(Unit)
    }*/

}
