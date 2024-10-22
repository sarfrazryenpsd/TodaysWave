package com.example.todayswave.domain.repository

import com.example.todayswave.data.response.TodaysWaveResponse
import retrofit2.Response

interface TodaysWaveRepository {
    suspend fun getNews(
        country: String?,
        language: String,
        text: String?
    ): Response<TodaysWaveResponse>
}