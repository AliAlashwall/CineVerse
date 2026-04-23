package com.example.cineverse.di

import android.content.Context
import com.example.cineverse.util.TokenStorage
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
    fun provideTokenStorage(@ApplicationContext context: Context): TokenStorage =
        TokenStorage(context = context)


    @Provides
    @Singleton
    suspend fun provideTokenApprovement(tokenStorage : TokenStorage): String {
        val accessToken = tokenStorage.getAccessToken()
        return "https://www.themoviedb.org/authenticate/{$accessToken}"
    }
}

