package com.example.todayswave.di

import com.example.todayswave.data.repository.TodaysWaveRepositoryImpl
import com.example.todayswave.data.web.TodaysWaveApi
import com.example.todayswave.domain.repository.TodaysWaveRepository
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
    fun provideTodaysWaveRepository(api: TodaysWaveApi): TodaysWaveRepository {
        return TodaysWaveRepositoryImpl(api)
    }

}