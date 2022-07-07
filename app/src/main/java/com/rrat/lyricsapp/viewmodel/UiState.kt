package com.rrat.lyricsapp.viewmodel

sealed class UiState {
    object Loading :UiState()
    data class Success<T>(val obj: T) : UiState()
    data class Error(val message: String) : UiState()
}