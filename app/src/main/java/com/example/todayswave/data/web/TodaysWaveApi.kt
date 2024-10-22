package com.example.todayswave.data.web

import com.example.todayswave.data.response.TodaysWaveResponse
import com.example.todayswave.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TodaysWaveApi {

    @GET("search-news")
    suspend fun getNews(
        @Query("country") country: String?,
        @Query("language") language: String,
        @Query("text") text: String?,
        @Query("news-sources") newsSources: String? = "https://www.bbc.co.uk",
        @Query("api-key") apiKey: String = API_KEY
    ): Response<TodaysWaveResponse>
}