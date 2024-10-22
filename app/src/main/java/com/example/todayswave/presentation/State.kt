package com.example.todayswave.presentation

sealed class State <out T> {
    data object Loading : State<Nothing>()
    class Success<T>(val data: T): State<T>()
    class Error(val error: String): State<Nothing>()
}