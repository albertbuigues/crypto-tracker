package com.buiguesortola.cryptotracker.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buiguesortola.cryptotracker.domain.CoinFilter
import com.buiguesortola.cryptotracker.domain.CryptoError
import com.buiguesortola.cryptotracker.domain.usecases.GetTopTenBestCoinsUseCase
import com.buiguesortola.cryptotracker.domain.usecases.GetTopTenWorstCoinsUseCase
import com.buiguesortola.cryptotracker.ui.events.UiEvent
import com.buiguesortola.cryptotracker.ui.states.CryptoCoinsListUIState
import com.buiguesortola.cryptotracker.ui.states.UiState
import com.buiguesortola.cryptotracker.ui.states.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoCoinsListViewModel @Inject constructor(
    private val getTopTenBestCoinsUseCase: GetTopTenBestCoinsUseCase,
    private val getWorstTenCoinsUseCase: GetTopTenWorstCoinsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<CryptoCoinsListUIState>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvents = Channel<UiEvent>()
    val uiEvents = _uiEvents.receiveAsFlow()

    init {
        fetchCryptoCoins()
    }

    private fun fetchCryptoCoins(filter: CoinFilter = CoinFilter.TOP_TEN) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            val result = if (filter == CoinFilter.TOP_TEN) getTopTenBestCoinsUseCase()
            else getWorstTenCoinsUseCase()

            result.fold(
                onSuccess = { coins ->
                    val coinsToUiState = coins.map { it.toUiState() }

                    _uiState.value = UiState.Success(
                        CryptoCoinsListUIState(
                            selectedChip = filter,
                            coinsList = coinsToUiState
                        )
                    )
                },
                onFailure = { error ->
                    _uiState.value = UiState.Error
                    when (error) {
                        is CryptoError.NetworkError -> _uiEvents.send(UiEvent.ShowNetworkError)
                        is CryptoError.EuroRateNotFoundError -> _uiEvents.send(UiEvent.ShowEuroNotFoundError)
                        else -> _uiEvents.send(UiEvent.ShowGenericError)
                    }
                }
            )
        }
    }

    fun refreshData() {
        val currentState = uiState.value
        if (currentState !is UiState.Success) {
            // We are in empty screen
            fetchCryptoCoins()
            return
        }
        fetchCryptoCoins(currentState.content.selectedChip)
    }

    fun manageFilterState(newSelectedFilter: CoinFilter) {
        val currentState = uiState.value
        if (currentState is UiState.Success) {
            val currentData = currentState.content
            if (currentData.selectedChip == newSelectedFilter) return
            _uiState.value = UiState.Success(currentData.copy(selectedChip = newSelectedFilter))
            fetchCryptoCoins(newSelectedFilter)
        }
    }
}