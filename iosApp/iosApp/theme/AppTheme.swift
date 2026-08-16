//
//  AppTheme.swift
//  iosApp
//
//  Created by GitHub Copilot on 14/08/2026.
//

import SwiftUI

// MARK: - Colors
struct AppColors {
    /// Background Primary Color - Dark Teal #103D36
    static let backgroundPrimary = Color(red: 0x10 / 255, green: 0x3D / 255, blue: 0x36 / 255)
    
    /// Primary Color - Dark Teal #103D36
    static let primary = Color(red: 0x10 / 255, green: 0x3D / 255, blue: 0x36 / 255)
    
    /// Selected Accent - Cyan/Light Teal #34E1CB
    static let selectedAccent = Color(red: 0x34 / 255, green: 0xE1 / 255, blue: 0xCB / 255)
    
    /// Positive Green - #076209
    static let positiveGreen = Color(red: 0x07 / 255, green: 0x62 / 255, blue: 0x09 / 255)
    
    /// Negative Red - #860B0B
    static let negativeRed = Color(red: 0x86 / 255, green: 0x0B / 255, blue: 0x0B / 255)
    
    /// Text Primary - Black / Dark gray for light mode
    static let textPrimary = Color(.label)
    
    /// Text Secondary - Gray
    static let textSecondary = Color(.secondaryLabel)
    
    /// Surface Background - White / Dark background for dark mode
    static let surface = Color(.systemBackground)
}

// MARK: - Typography
struct AppTypography {
    /// Display Large - For major headings (36sp equivalent)
    static let displayLarge = Font.system(size: 32, weight: .bold, design: .default)
    
    /// Headline - For section headers (24sp equivalent)
    static let headline = Font.system(size: 24, weight: .semibold, design: .default)
    
    /// Title - For subsections (20sp equivalent)
    static let title = Font.system(size: 20, weight: .semibold, design: .default)
    
    /// Body Medium - Standard text (16sp, matching Android's bodyMedium)
    static let bodyMedium = Font.system(size: 15, weight: .regular, design: .default)
    
    /// Body Small - Smaller text (14sp)
    static let bodySmall = Font.system(size: 13, weight: .regular, design: .default)
    
    /// Label - For buttons, chips, tags (14sp)
    static let label = Font.system(size: 14, weight: .medium, design: .default)
    
    /// Caption - Very small text (12sp)
    static let caption = Font.system(size: 12, weight: .regular, design: .default)
}

// MARK: - App Theme Provider (for environment)
struct AppTheme {
    let colors: AppColors.Type = AppColors.self
    let typography: AppTypography.Type = AppTypography.self
}

// MARK: - Environment Key for Theme Access
struct AppThemeKey: EnvironmentKey {
    static let defaultValue = AppTheme()
}

extension EnvironmentValues {
    var appTheme: AppTheme {
        get { self[AppThemeKey.self] }
        set { self[AppThemeKey.self] = newValue }
    }
}

// MARK: - View Extensions for Easy Access
extension View {
    func withAppTheme() -> some View {
        self.environment(\.appTheme, AppTheme())
    }
}
