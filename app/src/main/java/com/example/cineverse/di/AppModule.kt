package com.example.cineverse.di

import android.content.Context
import com.example.cineverse.data.local.dataStore.AuthStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTokenStorage(@ApplicationContext context: Context): AuthStorage =
        AuthStorage(context = context)


    @Provides
    @Singleton
    suspend fun provideTokenApprovement(authStorage : AuthStorage): String {
        val accessToken = authStorage.getAccessToken()
        return "https://www.themoviedb.org/authenticate/{$accessToken}"
    }
}

