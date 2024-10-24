package com.example.todayswave.presentation.todaysWaveDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todayswave.data.database.TodaysWaveDatabase
import com.example.todayswave.data.model.News
import com.example.todayswave.data.response.TodaysWaveResponse
import com.example.todayswave.presentation.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodaysWaveDetailsViewModel @Inject constructor(
    database: TodaysWaveDatabase
): ViewModel() {
    private val _state = MutableStateFlow<State<BookmarkAction>>(State.Loading)
    val state = _state as StateFlow<State<BookmarkAction>>

    private val newsDao = database.todaysWaveDao()

    fun addNews(news: News){
        viewModelScope.launch {
            try {
                _state.tryEmit(State.Loading)
                newsDao.addNews(news)
                _state.tryEmit(State.Success(BookmarkAction.ADD))
            } catch (e: Exception) {
                _state.tryEmit(State.Error(e.message.toString()))
            }
        }
    }

    fun removeNews(news: News){
        viewModelScope.launch {
            try {
                _state.tryEmit(State.Loading)
                newsDao.deleteNews(news)
                _state.tryEmit(State.Success(BookmarkAction.REMOVE))
            } catch (e: Exception) {
                _state.tryEmit(State.Error(e.message.toString()))
            }
        }
    }
}

enum class BookmarkAction{
    ADD, REMOVE
}