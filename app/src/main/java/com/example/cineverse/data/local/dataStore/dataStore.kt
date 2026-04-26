package com.example.cineverse.data.local.dataStore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

// Extension to create a single DataStore instance per app

val Context.dataStore by preferencesDataStore(name = "app_prefs")