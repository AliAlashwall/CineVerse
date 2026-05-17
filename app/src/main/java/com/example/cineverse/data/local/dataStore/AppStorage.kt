package com.example.cineverse.data.local.dataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppStorage @Inject constructor(
    @param:ApplicationContext private val context: Context
) {

    private val onBoardingKey = booleanPreferencesKey("onboarding_completed")
    private val isDarkThemeKey = booleanPreferencesKey("theme_mode")

    fun isOnBoardingCompleted(): Flow<Boolean> {
        return context.dataStore.data.map { pref ->
            pref[onBoardingKey] ?: false
        }
    }

    suspend fun setOnBoardingCompleted() {
        context.dataStore.edit { pref ->
            pref[onBoardingKey] = true
        }
    }

    fun getSavedTheme(): Flow<Boolean?> {
        return context.dataStore.data.map { preferences ->
            preferences[isDarkThemeKey]
        }
    }

    suspend fun setAppTheme(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[isDarkThemeKey] = isDark
        }
    }
}
