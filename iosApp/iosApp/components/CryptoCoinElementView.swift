//
//  CryptoCoinElementView.swift
//  iosApp
//
//  Created by Albert Buigues Ortolà on 16/08/2026.
//

import SwiftUI

struct CryptoCoinElementView: View {
    let coinName: String
    let priceInEuro: String
    let symbol: String
    let changePercentage: String
    
    private var isPositive: Bool {
        (Double(changePercentage) ?? 0.0) > 0
    }
    private var priceColor: Color {
        isPositive ? AppColors.positiveGreen : AppColors.negativeRed
    }
    
    var body: some View {
        VStack {
            HStack {
                Text(coinName)
                    .foregroundColor(AppColors.primary)
                    .frame(maxWidth: .infinity, alignment: .leading)
                Text(priceInEuro)
                    .foregroundColor(AppColors.primary)
                    .frame(maxWidth: .infinity, alignment: .trailing)
            }
            HStack {
                Text(symbol)
                    .foregroundColor(AppColors.primary)
                    .frame(maxWidth: .infinity, alignment: .leading)
                Text("\(changePercentage)%")
                    .foregroundColor(priceColor)
                    .frame(maxWidth: .infinity, alignment: .trailing)
            }
        }
        .padding(12.0)
        .frame(maxWidth: .infinity)
        .background(Color.white)
        .cornerRadius(2.0)
    }
}

#Preview {
    VStack {
        CryptoCoinElementView(
            coinName: "Bitcoin",
            priceInEuro: "52000.00",
            symbol: "BTC",
            changePercentage: "0.25"
        )
        
        CryptoCoinElementView(
            coinName: "Bitcoin",
            priceInEuro: "52000.00",
            symbol: "BTC",
            changePercentage: "-0.25"
        )
    }
    .padding()
    .background(Color.gray.opacity(0.2))
    .fixedSize(horizontal: false, vertical: true)
}
