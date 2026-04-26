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
    @ApplicationContext private val context: Context
) {

    private val dataStore = context.dataStore

    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val ACCESS_TOKEN_EXPIRY_DAY = stringPreferencesKey("access_token_expiry_day")

        val LOGIN_SUCCESSFULLY = booleanPreferencesKey("login_successfully")

        val SESSION_ID = stringPreferencesKey("Session_id")

        val SESSION_EXPIRY_DAY = stringPreferencesKey("session_expiry_day")
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

    suspend fun getLoginState(): Boolean? =
        dataStore.data.map { it[LOGIN_SUCCESSFULLY] }.firstOrNull()

    // In AuthStorage.kt
    val authDataFlow: Flow<Pair<String?, Boolean?>> = dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { prefs ->
            Pair(prefs[ACCESS_TOKEN], prefs[LOGIN_SUCCESSFULLY])
        }

    val isLoggedInFlow: Flow<Boolean> = dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { prefs -> prefs[ACCESS_TOKEN] != null }

    // One-shot reads for the Ktor plugin (suspend, not Flow)
    suspend fun getAccessToken(): String? =
        dataStore.data.map { it[ACCESS_TOKEN] }.firstOrNull()

    suspend fun getAccessTokenExpiryDay(): String? =
        dataStore.data.map { it[ACCESS_TOKEN_EXPIRY_DAY] }.firstOrNull()


    suspend fun isTokenExpired(): Boolean {
        //TO-DO
        return false
    }

    suspend fun clearAll() {
        dataStore.edit { it.clear() }
    }
}
