//
//  CryptoCoinsListView.swift
//  iosApp
//
//  Created by GitHub Copilot on 14/08/2026.
//

import SwiftUI
import SharedApp

// MARK: - Stateful Component (Container)
/// Stateful wrapper that manages ViewModel injection via Koin factory.
/// This component handles all business logic and state management.
struct CryptoCoinsListViewStateful: View {
    @StateObject private var adapter: CryptoCoinsListViewModelAdapter
    
    init() {
        let viewModel = KoinHelper().getCryptoCoinsListViewModel()
        _adapter = StateObject(wrappedValue: CryptoCoinsListViewModelAdapter(viewModel: viewModel))
    }
    
    var body: some View {
        CryptoCoinsListViewStateless(
            isLoading: adapter.isLoading,
            selectedFilter: adapter.selectedFilter,
            coins: adapter.coins,
            onFilterSelected: adapter.selectFilter(_:),
            onRefresh: adapter.refresh
        )
    }
}

// MARK: - Stateless Component (Presentation)
/// Stateless presentation component that only receives data and callbacks.
/// This component has no dependencies on the ViewModel and is easy to test/preview.
struct CryptoCoinsListViewStateless: View {
    let isLoading: Bool
    let selectedFilter: Int8
    let coins: [CoinUiModel]
    let onFilterSelected: (Int8) -> Void
    let onRefresh: () -> Void

    var body: some View {
        ZStack {
            AppColors.backgroundPrimary
                .ignoresSafeArea()
            
            VStack(spacing: 0) {
                if isLoading {
                    LoadingView()
                } else if coins.isEmpty {
                    EmptyView(
                        emptyScreenText: LocalizedStringKey("empty_text"),
                        onRefresh: onRefresh
                    )
                } else {
                    VStack(spacing: 0) {
                        VStack(spacing: 16) {
                            HStack(spacing: 12) {
                                FilterChipView(
                                    id: "chip_best",
                                    text: LocalizedStringKey("best_coins"),
                                    isSelected: selectedFilter == 0,
                                    iconName: "arrow.up"
                                ) {
                                    if selectedFilter == 0 { return }
                                    onFilterSelected(0)
                                }

                                FilterChipView(
                                    id: "chip_worst",
                                    text: LocalizedStringKey("worst_coins"),
                                    isSelected: selectedFilter == 1,
                                    iconName: "arrow.down"
                                ) {
                                    if (selectedFilter == 1) { return }
                                    onFilterSelected(1)
                                }
                            }
                            .frame(maxWidth: .infinity)
                            .padding(.horizontal, 16)
                        }
                        .padding(.vertical, 16)
                        .background(AppColors.backgroundPrimary)

                        ScrollView(.vertical, showsIndicators: false) {
                            LazyVStack(spacing: 8) {
                                ForEach(coins, id: \.id) { coin in
                                    CryptoCoinElementView(
                                        coinName: coin.name,
                                        priceInEuro: coin.priceInEuro,
                                        symbol: coin.symbol,
                                        changePercentage: coin.changePercentage
                                    )
                                }
                            }
                            .padding(.horizontal, 16)
                        }
                        .scrollClipDisabled()
                        .refreshable {
                            onRefresh()
                        }
                    }
                }
            }
            .safeAreaPadding(.bottom, 20.0)
        }
    }
}

// MARK: - Preview (Stateless)
#if DEBUG
struct CryptoCoinsListViewStateless_Previews: PreviewProvider {
    static var previews: some View {
        CryptoCoinsListViewStateless(
            isLoading: false,
            selectedFilter: 0,
            coins: [
                CoinUiModel.init(from:
                    CoinUiState(
                        name: "Bitcoin",
                        symbol: "BTC",
                        priceInEuro: 52000.00,
                        changePercentage: 0.25,
                        changePercentFormatted: "0.25%"
                    )
                ),
                CoinUiModel.init(from:
                    CoinUiState(
                        name: "Bitcoin",
                        symbol: "BTC",
                        priceInEuro: 52000.00,
                        changePercentage: 0.25,
                        changePercentFormatted: "0.25%"
                    )
                ),
                CoinUiModel.init(from:
                    CoinUiState(
                        name: "Bitcoin",
                        symbol: "BTC",
                        priceInEuro: 52000.00,
                        changePercentage: 0.25,
                        changePercentFormatted: "0.25%"
                    )
                ),
                CoinUiModel.init(from:
                    CoinUiState(
                        name: "Bitcoin",
                        symbol: "BTC",
                        priceInEuro: 52000.00,
                        changePercentage: 0.25,
                        changePercentFormatted: "0.25%"
                    )
                ),
                CoinUiModel.init(from:
                    CoinUiState(
                        name: "Bitcoin",
                        symbol: "BTC",
                        priceInEuro: 52000.00,
                        changePercentage: 0.25,
                        changePercentFormatted: "0.25%"
                    )
                ),
                CoinUiModel.init(from:
                    CoinUiState(
                        name: "Bitcoin",
                        symbol: "BTC",
                        priceInEuro: 52000.00,
                        changePercentage: 0.25,
                        changePercentFormatted: "0.25%"
                    )
                ),
                CoinUiModel.init(from:
                    CoinUiState(
                        name: "Bitcoin",
                        symbol: "BTC",
                        priceInEuro: 52000.00,
                        changePercentage: 0.25,
                        changePercentFormatted: "0.25%"
                    )
                ),
                CoinUiModel.init(from:
                    CoinUiState(
                        name: "Bitcoin",
                        symbol: "BTC",
                        priceInEuro: 52000.00,
                        changePercentage: 0.25,
                        changePercentFormatted: "0.25%"
                    )
                ),
                CoinUiModel.init(from:
                    CoinUiState(
                        name: "Bitcoin",
                        symbol: "BTC",
                        priceInEuro: 52000.00,
                        changePercentage: 0.25,
                        changePercentFormatted: "0.25%"
                    )
                ),
                CoinUiModel.init(from:
                    CoinUiState(
                        name: "Bitcoin",
                        symbol: "BTC",
                        priceInEuro: 52000.00,
                        changePercentage: 0.25,
                        changePercentFormatted: "0.25%"
                    )
                ),
            ],
            onFilterSelected: { _ in },
            onRefresh: {}
        )
        .previewDisplayName("Crypto List - Stateless Preview")
    }
}
#endif
