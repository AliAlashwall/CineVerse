package com.example.cineverse.data.local.dataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

// Extension to create a single DataStore instance per app
val Context.dataStore by preferencesDataStore(name = "auth_prefs")

class TokenStorage(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val ACCESS_TOKEN_EXPIRY_DAY = stringPreferencesKey("access_token_expiry_day")

        val SESSION_ID = stringPreferencesKey("Session_id")

        val SESSION_EXPIRY_DAY = stringPreferencesKey("session_expiry_day")
    }

    // Expose as Flow for reactive UI
    val accessTokenFlow: Flow<String?> = dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { it[ACCESS_TOKEN] }

    val isLoggedInFlow: Flow<Boolean> = dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { prefs -> prefs[ACCESS_TOKEN] != null }

    // One-shot reads for the Ktor plugin (suspend, not Flow)
    suspend fun getAccessToken(): String? =
        dataStore.data.map { it[ACCESS_TOKEN] }.firstOrNull()

    suspend fun getAccessTokenExpiryDay(): String? =
        dataStore.data.map { it[ACCESS_TOKEN_EXPIRY_DAY] }.firstOrNull()


    suspend fun isTokenExpired(): Boolean {
        //TODO
        return false
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

    suspend fun saveSessionData(
        sessionExpiryDay: String
    ) {
        dataStore.edit { prefs ->
            prefs[SESSION_EXPIRY_DAY] = sessionExpiryDay
        }
    }

    suspend fun saveSessionId(sessionId: String) {
        dataStore.edit { it[SESSION_ID] = sessionId }
    }

    suspend fun getSessionId(): String? =
        dataStore.data.map { it[SESSION_ID] }.firstOrNull()

    suspend fun clearAll() {
        dataStore.edit { it.clear() }
    }
}
