plugins {
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    // javax.inject for @Inject annotations (pure JVM, no Android)
    implementation(libs.javax.inject)
    // Testing
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit)
    testRuntimeOnly(libs.junit.vintage)
    testImplementation(libs.mockk)
}

tasks.withType<Test> {
    useJUnitPlatform()
}