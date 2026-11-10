package com.buiguesortola.cryptotracker.ui.events

sealed class UiEvent {
    object ShowNetworkError: UiEvent()
    object ShowGenericError: UiEvent()
    object ShowEuroNotFoundError: UiEvent()
}