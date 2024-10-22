package com.example.todayswave.data.repository

import com.example.todayswave.data.response.TodaysWaveResponse
import com.example.todayswave.data.web.TodaysWaveApi
import com.example.todayswave.domain.repository.TodaysWaveRepository
import retrofit2.Response
import javax.inject.Inject

class TodaysWaveRepositoryImpl @Inject constructor(private val api: TodaysWaveApi): TodaysWaveRepository {
    override suspend fun getNews(
        country: String?,
        language: String,
        text: String?
    ): Response<TodaysWaveResponse> {
        return api.getNews(country, language, text)
    }
}