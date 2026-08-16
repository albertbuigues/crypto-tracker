//
//  FilterChipView.swift
//  iosApp
//
//  Created by GitHub Copilot on 14/08/2026.
//
import SwiftUI

struct FilterChipView: View {
    let id: String
    let text: LocalizedStringKey
    let isSelected: Bool
    let iconName: String
    let action: () -> Void

    var body: some View {
        Button(action: action) {
            HStack(spacing: 8) {
                Image(systemName: iconName)
                    .font(.system(size: 14, weight: .semibold))
                    .foregroundColor(AppColors.primary)
                
                Text(text)
                    .font(AppTypography.label)
                    .foregroundColor(AppColors.primary)
            }
            .padding(.vertical, 8)
            .padding(.horizontal, 16)
            .background(Color.white)
            .cornerRadius(16)
            .overlay(
                RoundedRectangle(cornerRadius: 16)
                    .stroke(
                        isSelected ? AppColors.selectedAccent : Color.clear,
                        lineWidth: 2
                    )
            )
        }
        .buttonStyle(PlainButtonStyle())
        .accessibilityIdentifier(id)
    }
}

// MARK: - Preview
#if DEBUG
struct FilterChipView_Previews: PreviewProvider {
    static var previews: some View {
        HStack(spacing: 12) {
            FilterChipView(
                id: "chip_best",
                text: LocalizedStringKey("best_coins"),
                isSelected: true,
                iconName: "arrow.up"
            ) {}
            
            FilterChipView(
                id: "chip_worst",
                text: LocalizedStringKey("worst_coins"),
                isSelected: false,
                iconName: "arrow.down"
            ) {}
        }
        .padding()
        .background(Color.gray)
        .previewLayout(.sizeThatFits)
        .previewDisplayName("Filter Chips")
    }
}
#endif
