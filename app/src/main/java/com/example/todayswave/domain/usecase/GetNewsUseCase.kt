package com.example.todayswave.domain.usecase

import com.example.todayswave.data.response.TodaysWaveResponse
import com.example.todayswave.domain.repository.TodaysWaveRepository
import javax.inject.Inject


class GetNewsUseCase @Inject constructor(
    private val todaysWaveRepository: TodaysWaveRepository
) {
    suspend operator fun invoke(
        country: String?,
        language: String,
        text: String?
    ) :TodaysWaveResponse {
        val response = todaysWaveRepository.getNews(country, language, text)
        if (response.body() == null){
            when(response.code()){
                404 -> throw Exception("No News found")
                500 -> throw Exception("Server Error")
                401 -> throw Exception("Unauthorized")
                400 -> throw Exception("Bad Request")
                403 -> throw Exception("Forbidden")
                else -> throw Exception("No News Found")
            }
        }
        return todaysWaveRepository.getNews(country, language, text).body()!!
    }
}