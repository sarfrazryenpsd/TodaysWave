package com.example.todayswave.data.response

import com.example.todayswave.data.model.News

data class TodaysWaveResponse(
    val available: Int,
    val news: List<News>,
    val number: Int,
    val offset: Int
)