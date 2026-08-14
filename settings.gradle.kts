pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        // JetBrains Compose Multiplatform artifacts are published to this repository
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        // Required for Compose Multiplatform artifacts (material3, etc.)
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenCentral()
    }
}

rootProject.name = "Crypto Coins List"
include(":app")
include(":network")
include(":domain")
