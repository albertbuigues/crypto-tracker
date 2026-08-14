//
//  CriptoCoinsApp.swift
//  CriptoCoins
//
//  Created by Albert Buigues Ortolà on 14/08/2026.
//

import SwiftUI
import SharedApp

@main
struct CriptoCoinsApp: App {
    
    init() {
        KoinHelperKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
