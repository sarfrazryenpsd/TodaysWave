package com.example.todayswave.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todayswave.data.dao.TodaysWaveDao
import com.example.todayswave.data.model.News

@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class TodaysWaveDatabase: RoomDatabase() {
    abstract fun todaysWaveDao(): TodaysWaveDao

    companion object{
        const val DATABASE_NAME = "tw_db"

        @JvmStatic
        fun getDatabase(context: Context): TodaysWaveDatabase {
            return Room.databaseBuilder(
                context,
                TodaysWaveDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}