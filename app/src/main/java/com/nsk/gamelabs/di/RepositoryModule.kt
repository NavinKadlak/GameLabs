package com.nsk.gamelabs.di

import com.nsk.gamelabs.core.data.remote.api.ApiService
import com.nsk.gamelabs.core.data.repository.GameRepositoryImpl
import com.nsk.gamelabs.core.domain.repository.GameRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {



    @Binds
    abstract fun bindGameRepository(
        impl: GameRepositoryImpl // The actual class
    ): GameRepository // The interface requested by ViewModels


}