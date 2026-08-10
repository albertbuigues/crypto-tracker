# 🏗 Project Architecture Documentation

This document describes the architectural patterns, module structure, and technical decisions made during the development of the Bitpanda Live Challenge.

## 🚀 Setup & Configuration

To run this project, you need to provide a valid API Token in your local environment to authorize network requests.

1. Open your local.properties file in the root directory.
2. Add the following line:
   API_TOKEN=your_api_token
3. Sync Gradle and run the app. The project is configured to inject this value into the BuildConfig during the compilation process.

## 1. Architectural Pattern: Clean Architecture
The project is built following **Clean Architecture** principles. The main goal is the **Separation of Concerns**, ensuring that the business logic is isolated from external factors like UI frameworks, databases, or network providers.

### The Dependency Rule
The core business logic (Domain) knows nothing about the UI or the Network implementation.
* **Presentation (UI)** depends on **Domain** and on **Network** only for Hilt purposes
* **Data (Network)** depends on **Domain**.
* **Domain** depends on **nothing**.

---

## 2. Multi-Module Structure
To enforce the Clean Architecture boundaries and improve build times, the project is divided into several Gradle modules:

### 🧩 `:domain`
The most stable and important module.
* **Models:** Pure Kotlin data classes (e.g., `Coin`).
* **Repository Interfaces:** Contracts that define how data should be fetched.
* **Use Cases:** Business Logic units (e.g., `GetTopBestCoinsUseCase`).

### 🌐 `:network`
Handles all external data communication.
* **Data Sources:** Retrofit api interfaces.
* **DTOs:** Data Transfer Objects to parse API responses.
* **Mappers:** Logic to convert DTOs into Domain Models.
* **Repository Implementation:** Orchestrates data fetching, error handling, and currency conversion.

### 📱 `:app` (Presentation)
The presentation layer built with **Jetpack Compose**.
* **State Management:** Uses ViewModels and `StateFlow` to implement the Unidirectional Data Flow (UDF) pattern.
* **Use of State Hoisting pattern to separate Stateless than Stateful composables
* **Dependency Injection:** Hilt is configured here at the Application Class and ViewModels and Android Entry Point

### ⚡ Advanced UI & UX Polish
* **Swipe-to-Refresh:** Integrated using Material 3 PullToRefreshContainer. The lifecycle of the refresh animation is bound to the ViewModel's asynchronous state to ensure perfect synchronization.** Retrofit api interfaces.
* **DTOs:** Data Transfer Objects to parse API responses.
* **Mappers:** Logic to convert DTOs into Domain Models.
* **Repository Implementation:** Orchestrates data fetching, error handling, and currency conversion.

---

## 3. Key Technical Decisions

### 💶 Currency Integrity (EUR Conversion)
The API provides prices in USD, but the requirements specify EUR.
* **Decision:** The mapping logic is centralized in the Repository.
* **Logic:** A coin is only emitted if a valid EUR exchange rate is available. If the conversion fails, the data is treated as inconsistent to prevent showing wrong financial information.

### ⚡ Reactive UI with Compose
By migrating from legacy XML/Fragments to **Jetpack Compose**:
* Reduced boilerplate code (no more Adapters or ViewHolders).
* State-driven UI, making the application more predictable and easier to debug.
* **Swipe-to-Refresh:** Integrated using Material 3 PullToRefreshContainer. The lifecycle of the refresh animation is bound to the ViewModel's asynchronous state to ensure perfect synchronization.
* **Anti-Flickering Strategy (Min loading time):** I've implemented a 500ms minimum loading duration. Given the high performance of the modern stack and the API, near-instant transitions between Loading and Success states can be perceived by the user as UI glitches. This deliberate delay ensures a smooth, professional, and deliberate user experience.
* **Stateful vs Stateless:** All UI components follow the State Hoisting pattern, strictly separating logic from rendering to improve reusability and testability.
* Branding for colors has been taken from Bitpanda webpage

### 🛡️ Use Case Granularity
Instead of a single large Use Case, I implemented specific ones for each filter (Best, Worst, All).
* **Why:** This follows the **Single Responsibility Principle (SRP)** and makes Unit Testing significantly easier, as each sorting/filtering logic can be tested in isolation.

---

## 4. Others

I've created a task to execute all unit tests of all modules
./gradlew testAllModules

## 5. Tech Stack
* **Language:** Kotlin
* **UI:** Jetpack Compose (Material 3)
* **DI:** Hilt
* **Networking:** Retrofit
* **Concurrency:** Kotlin Coroutines & Flow API

## Videos for demonstration (Success case and Error Case when Fetching data because of Network error)

Success:

https://github.com/user-attachments/assets/6c116aa0-b86b-441e-ac80-f9f06c4982aa

Error:

https://github.com/user-attachments/assets/70ebe484-21b6-41f2-8c44-9dc84c1b9ee2





