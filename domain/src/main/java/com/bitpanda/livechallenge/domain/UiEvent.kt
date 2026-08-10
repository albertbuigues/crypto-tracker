package com.bitpanda.livechallenge.domain

sealed class UiEvent {
    object ShowNetworkError: UiEvent()
    object ShowGenericError: UiEvent()
    object ShowEuroNotFoundError: UiEvent()
}