package com.buiguesortola.cryptotracker.ui.states

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<out T>(val content: T) : UiState<T>()
    data object Error : UiState<Nothing>()
}
