package com.example.todayswave.utils

import android.os.Build
import android.util.Log
import com.example.todayswave.data.model.News
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.Base64

object NavRoute {
    fun createTodaysWaveDetails(news: News, isLocal: Boolean? = false): String {
        val encodedImage = URLEncoder.encode(news.image, "utf-8")
        val encodedUrl = URLEncoder.encode(news.url, "utf-8")
        val tempNews = news.copy(
            image = encodedImage, url = encodedUrl,
            title = encodeString(news.title),
            text = encodeString(news.text)
        )
        val gson = Gson().toJson(tempNews)
        return "/detail/news=$gson&isLocal=$isLocal"
    }

    fun getNewsFromRoute(json: String): News {
        val news = Gson().fromJson(json, News::class.java)
        val decodedImage = URLDecoder.decode(news.image, "utf-8")
        val decodeUrl = URLDecoder.decode(news.url, "utf-8")
        return news.copy(
            image = decodedImage, url = decodeUrl,
            title = decodeString(news.title),
            text = decodeString(news.text)
        )
    }

    private fun encodeString(str: String): String {
        Log.d("NavRoute", "encodeString: $str")
        val encodedString = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String(Base64.getEncoder().encode(str.toByteArray()))
        } else {
            String(android.util.Base64.encode(str.toByteArray(), android.util.Base64.NO_WRAP))
        }
        return encodedString.replace("/", "_")
    }

    private fun decodeString(str: String): String {
        Log.d("NavRoute", "decodeString: $str")
        val updatedString =  str.replace("_","/")
        val decoded = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String(Base64.getDecoder().decode(updatedString))
        } else {
            String(android.util.Base64.decode(updatedString, android.util.Base64.NO_WRAP))
        }
        return decoded
    }
}