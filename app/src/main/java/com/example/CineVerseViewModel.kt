package com.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineverse.data.local.dataStore.OnBoardingStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CineVerseViewModel @Inject constructor(
    val onBoardingPref: OnBoardingStorage
) : ViewModel() {

    val isOnBoardingCompleted = onBoardingPref.isOnBoardingCompleted().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    fun setOnBoardingCompleted() {
        viewModelScope.launch { onBoardingPref.setOnBoardingCompleted() }

    }

}