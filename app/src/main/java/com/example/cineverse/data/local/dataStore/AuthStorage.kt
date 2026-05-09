package com.example.cineverse.data.local.dataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthStorage @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore

    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val ACCESS_TOKEN_EXPIRY_DAY = stringPreferencesKey("access_token_expiry_day")

        val LOGIN_SUCCESSFULLY = booleanPreferencesKey("login_successfully")

        val SESSION_ID = stringPreferencesKey("Guest_session_id")

        val SESSION_EXPIRY_DAY = stringPreferencesKey("Guest_session_expiry_day")
    }

    suspend fun saveAccessToken(
        accessToken: String,
        tokenExpiryDay: String
    ) {
        dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN] = accessToken
            prefs[ACCESS_TOKEN_EXPIRY_DAY] = tokenExpiryDay
        }
    }

    suspend fun saveLoginState(isLoginSuccessfully: Boolean) {
        dataStore.edit { it[LOGIN_SUCCESSFULLY] = isLoginSuccessfully }
    }

    suspend fun saveSessionData(
        sessionId: String,
        sessionExpiryDay: String = "Not Found"
    ) {
        dataStore.edit { prefs ->
            prefs[SESSION_ID] = sessionId
            prefs[SESSION_EXPIRY_DAY] = sessionExpiryDay
        }
    }


    suspend fun getSessionId(): String? =
        dataStore.data.map { it[SESSION_ID] }.firstOrNull()


    // In AuthStorage.kt
    val authDataFlow: Flow<Pair<String?, Boolean?>> = dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { prefs ->
            Pair(
                prefs[ACCESS_TOKEN],
                prefs[LOGIN_SUCCESSFULLY]
            )
        }


    suspend fun getAccessToken(): String? =
        dataStore.data.map { it[ACCESS_TOKEN] }.firstOrNull()


    suspend fun clearAll() {
        dataStore.edit { it.clear() }
    }
}
