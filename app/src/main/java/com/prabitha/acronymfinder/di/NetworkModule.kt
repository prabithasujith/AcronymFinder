package com.prabitha.acronymfinder.di

import com.prabitha.acronymfinder.api.AcronymService
import com.prabitha.acronymfinder.data.AcronymRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideAcronymServiceApi(): AcronymService {
        return AcronymService.create()
    }
}

@InstallIn(SingletonComponent::class)
@Module
class AcronymRepositoryModule {

    @Singleton
    @Provides
    fun provideAcronymRepository(acronymService: AcronymService): AcronymRepository {
        return AcronymRepository(acronymService)
    }
}