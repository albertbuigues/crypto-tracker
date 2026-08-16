//
//  CryptoCoinsListViewModelAdapter.swift
//  iosApp
//
//  Created by Albert Buigues Ortolà on 14/08/2026.
//
import Foundation
import SharedApp
import Combine
import SwiftUI

@MainActor
final class CryptoCoinsListViewModelAdapter: ObservableObject {
    @Published var isLoading: Bool = false
    @Published var coins: [CoinUiModel] = []
    @Published var selectedFilter: Int8 = 0
    @Published var alertMessage: String?

    private let viewModel: CryptoCoinsListViewModel
    private var uiStateTask: Task<Void, Never>?
    private var uiEventsTask: Task<Void, Never>?
    
    init(viewModel: CryptoCoinsListViewModel) {
        self.viewModel = viewModel
        startCollecting()
    }

    deinit {
        uiStateTask?.cancel()
        uiEventsTask?.cancel()
    }

    private func startCollecting() {
        uiStateTask = Task { [weak self] in
            guard let self = self else { return }
            for await state in self.viewModel.uiState {
                let typeName = String(describing: type(of: state))
                if typeName.contains("Loading") {
                    self.isLoading = true
                    self.coins = []
                } else if typeName.contains("Success") {
                    self.isLoading = false
                    // Try to read `content` property dynamically (works across SKIE mappings).
                    let mirror = Mirror(reflecting: state)
                    if let contentChild = mirror.children.first(where: { $0.label == "content" }),
                       let content = contentChild.value as? CryptoCoinsListUIState {
                        self.selectedFilter = content.selectedChip
                        self.coins = content.coinsList.map { CoinUiModel(from: $0) }
                    } else {
                        self.coins = []
                    }
                } else if typeName.contains("Error") {
                    self.isLoading = false
                    self.coins = []
                }
            }
        }
        
        uiEventsTask = Task { [weak self] in
            guard let self = self else { return }
            for await event in self.viewModel.uiEvents {
                self.alertMessage = String(describing: event)
            }
        }
    }

    func refresh() {
        viewModel.refreshData()
    }

    func selectFilter(_ id: Int8) {
        self.selectedFilter = id
        viewModel.manageFilterState(newSelectedFilter: id)
    }
}

struct CoinUiModel: Identifiable {
    let id = UUID()
    let name: String
    let symbol: String
    let priceInEuro: String
    let changePercentage: Double

    init(from shared: CoinUiState) {
        self.name = shared.name
        self.symbol = shared.symbol
        self.priceInEuro = "\(shared.priceInEuro) €"
        self.changePercentage = Double("\(shared.changePercentage)") ?? 0.0
    }
}
