package com.bitpanda.livechallenge.ui.states

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<out T>(val content: T) : UiState<T>()
    object Error : UiState<Nothing>()
}