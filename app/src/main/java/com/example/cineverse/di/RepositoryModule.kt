package com.example.cineverse.di

import com.example.cineverse.data.RepositoryImpl
import com.example.cineverse.domain.Repository
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
    fun provideRepository(impl: RepositoryImpl): Repository {
        return impl
    }

}