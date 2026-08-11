@file:OptIn(ExperimentalMaterial3Api::class)

package com.buiguesortola.cryptotracker.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buiguesortola.cryptotracker.R
import com.buiguesortola.cryptotracker.domain.UiEvent
import com.buiguesortola.cryptotracker.ui.states.UiState
import com.buiguesortola.cryptotracker.ui.viewmodels.CryptoCoinsListViewModel
import com.buiguesortola.cryptotracker.ui.theme.PrimaryColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun CryptoCoinsScreen(
    viewModel: CryptoCoinsListViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { event ->
            val resId = when (event) {
                is UiEvent.ShowNetworkError -> R.string.network_error
                is UiEvent.ShowEuroNotFoundError -> R.string.euro_not_found
                is UiEvent.ShowGenericError -> R.string.generic_error
            }
            val text = context.getString(resId)
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
        }
    }

    when(val currentState = state) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Success -> {
            val pullToRefreshState = rememberPullToRefreshState()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(pullToRefreshState.nestedScrollConnection)
            ) {
                StatefulCryptoCoinsList(
                    selectedChip = currentState.content.selectedChip,
                    onChipSelected = { filter -> viewModel.manageFilterState(filter) },
                    coinsList = currentState.content.coinsList
                )

                PullToRefreshContainer(
                    state = pullToRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                    containerColor = Color.White,
                    contentColor = PrimaryColor
                )

                LaunchedEffect(pullToRefreshState.isRefreshing) {
                    if (pullToRefreshState.isRefreshing) {
                        viewModel.refreshData()
                        pullToRefreshState.endRefresh()
                    }
                }
            }
        }
        is UiState.Error -> {
            // Logic to propagate error to analytics tool like Firebase or others
            // UI management is done in Launched Effect using Channels for one time events
            StatefulEmptyScreen(
                helperText = stringResource(R.string.empty_text),
                onRefresh = { viewModel.refreshData() }
            )
        }
    }
}