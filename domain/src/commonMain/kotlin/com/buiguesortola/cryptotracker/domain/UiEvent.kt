package com.buiguesortola.cryptotracker.domain

sealed class UiEvent {
    object ShowNetworkError: UiEvent()
    object ShowGenericError: UiEvent()
    object ShowEuroNotFoundError: UiEvent()
}