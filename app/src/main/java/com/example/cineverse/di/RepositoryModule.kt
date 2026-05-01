package com.example.cineverse.di

import com.example.cineverse.data.remote.repository.AuthRepositoryImpl
import com.example.cineverse.data.remote.repository.MoviesRepositoryImpl
import com.example.cineverse.domain.repository.AuthRepository
import com.example.cineverse.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository {
        return impl
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(impl: MoviesRepositoryImpl): MoviesRepository {
        return impl
    }
}