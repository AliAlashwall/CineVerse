package com.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineverse.data.local.dataStore.AppStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CineVerseViewModel @Inject constructor(
    private val appStorage: AppStorage
) : ViewModel() {

    val isOnBoardingCompleted = appStorage.isOnBoardingCompleted().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun setOnBoardingCompleted() {
        viewModelScope.launch { appStorage.setOnBoardingCompleted() }
    }

    private val _isDarkTheme = MutableStateFlow<Boolean?>(null)
    val isDarkTheme: StateFlow<Boolean?> = _isDarkTheme.asStateFlow()

    private val _isThemeLoaded = MutableStateFlow(false)
    val isThemeLoaded: StateFlow<Boolean> = _isThemeLoaded.asStateFlow()

    init {
        viewModelScope.launch {
            appStorage.getSavedTheme().collect { isDark ->
                _isDarkTheme.value = isDark
                _isThemeLoaded.value = true
            }
        }
    }

    fun setAppTheme(isDark: Boolean) {
        viewModelScope.launch { 
            appStorage.setAppTheme(isDark) 
        }
    }
}