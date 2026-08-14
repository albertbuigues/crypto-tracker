import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.skie)
}

android {
    compileSdk = 36

    defaultConfig {
        namespace = "com.buiguesortola.cryptotracker"
        applicationId = "com.buiguesortola.cryptotracker"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }

    val xcf = XCFramework("SharedApp")

    composeCompiler {
        targetKotlinPlatforms.set(
            setOf(
                org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.androidJvm,
            ),
        )
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "SharedApp"
            isStatic = true
            xcf.add(this)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":domain"))
            implementation(project(":network"))
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.coroutines.core)
        }

        androidMain.dependencies {
            // AndroidX Core
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.google.material)
            implementation(libs.androidx.lifecycle.runtime.ktx)
            // Compose
            implementation(libs.androidx.activity.compose)
            implementation(
                project.dependencies.platform(
                    libs.androidx.compose.bom
                        .get()
                        .toString(),
                ),
            )
            implementation(libs.androidx.compose.ui)
            implementation(libs.androidx.compose.ui.graphics)
            implementation(libs.androidx.compose.ui.tooling)
            implementation(libs.androidx.compose.ui.tooling.preview)
            implementation(libs.androidx.compose.material3)

            // Dependency Injection
            implementation(
                project.dependencies.platform(
                    libs.koin.bom
                        .get()
                        .toString(),
                ),
            )
            implementation(libs.koin.android)
            implementation(libs.koin.compose)

            // Lifecycle Components
            implementation(libs.androidx.lifecycle.viewmodel.ktx)

            // Coroutines
            implementation(libs.kotlinx.coroutines.android)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}
