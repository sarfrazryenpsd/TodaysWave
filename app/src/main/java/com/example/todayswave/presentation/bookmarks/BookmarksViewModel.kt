package com.example.todayswave.presentation.bookmarks

import androidx.lifecycle.ViewModel
import com.example.todayswave.data.database.TodaysWaveDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(database: TodaysWaveDatabase): ViewModel() {

    private val newsDao = database.todaysWaveDao()

    fun getBookmarks() = newsDao.getNews()

}