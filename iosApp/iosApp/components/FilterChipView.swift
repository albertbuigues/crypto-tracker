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
    let action: () -> Void

    var body: some View {
        Button(action: action) {
            Text(text)
                .font(.subheadline)
                .foregroundColor(isSelected ? .white : .primary)
                .padding(.vertical, 8)
                .padding(.horizontal, 12)
                .background(isSelected ? Color.accentColor : Color(.systemGray5))
                .cornerRadius(16)
        }
        .buttonStyle(PlainButtonStyle())
        .accessibilityIdentifier(id)
    }
}

// MARK: - Preview
#if DEBUG
struct FilterChipView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            HStack(spacing: 12) {
                FilterChipView(id: "chip_best", text: LocalizedStringKey("Example"), isSelected: true) {}
                FilterChipView(id: "chip_worst", text: LocalizedStringKey("Example"), isSelected: false) {}
            }
            .padding()
            .previewLayout(.sizeThatFits)
            .previewDisplayName("Chips - Light")
        }
    }
}
#endif
