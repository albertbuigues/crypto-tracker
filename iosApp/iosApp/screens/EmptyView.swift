//
//  EmptyView.swift
//  iosApp
//
//  Created by Albert Buigues Ortolà on 16/08/2026.
//

import SwiftUI

struct EmptyView: View {
    let emptyScreenText: LocalizedStringKey
    let onRefresh: () -> Void

    var body: some View {
        ZStack {
            Color.clear

            VStack(spacing: 8) {
                Image(systemName: "exclamationmark.triangle")
                    .font(.system(size: 60))
                    .foregroundColor(.white)

                Text(emptyScreenText)
                    .font(AppTypography.bodySmall)
                    .foregroundColor(.white)
                    .multilineTextAlignment(.center)
                    .padding(.horizontal, 40)
            }

            VStack {
                Spacer()
                Button(action: onRefresh) {
                    Text("refresh")
                        .font(AppTypography.label)
                        .foregroundColor(.white)
                        .padding(.vertical, 10)
                        .padding(.horizontal, 24)
                        .overlay(
                            RoundedRectangle(cornerRadius: 20)
                                .stroke(Color.white, lineWidth: 2)
                        )
                }
                .padding(.bottom, 32)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

#Preview {
    ZStack {
        AppColors.backgroundPrimary
        EmptyView(emptyScreenText: "empty_text") {}
    }
}
