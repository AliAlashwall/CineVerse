package com.example.cineverse.data.local.dataStore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnBoardingStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val onBoardingKey = booleanPreferencesKey("onboarding_completed")

    // if it still null, so this mean it's the first time so return false
    fun isOnBoardingCompleted(): Flow<Boolean> {
        return context.dataStore.data.map { pref ->
            pref[onBoardingKey] ?: false
        }
    }

    suspend fun setOnBoardingCompleted() {
        context.dataStore.edit { pref ->
            pref[onBoardingKey] = true
            Log.d("cineverse dataStore", "Login state saved successfully")
        }

    }
}