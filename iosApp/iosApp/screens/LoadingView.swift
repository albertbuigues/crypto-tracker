//
//  LoadingView.swift
//  iosApp
//
//  Created by Albert Buigues Ortolà on 16/08/2026.
//

import SwiftUI

struct LoadingView: View {
    var body: some View {
        ZStack {
            Color.clear
            ProgressView()
                .progressViewStyle(CircularProgressViewStyle(tint: .white))
                .scaleEffect(1.5)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

#Preview {
    ZStack {
        AppColors.backgroundPrimary
        LoadingView()
    }
}
