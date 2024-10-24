package com.example.todayswave.di

import android.content.Context
import com.example.todayswave.data.database.TodaysWaveDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): TodaysWaveDatabase {
        return TodaysWaveDatabase.getDatabase(context)
    }
}