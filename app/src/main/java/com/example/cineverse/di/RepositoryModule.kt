package com.example.cineverse.di

import com.example.cineverse.data.remote.repository.RepositoryImpl
import com.example.cineverse.domain.repository.Repository
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