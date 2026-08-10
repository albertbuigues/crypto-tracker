// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}

tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}

subprojects {
    tasks.withType<Test> {
        testLogging {
            events("passed", "skipped", "failed", "standardOut", "standardError")
            showStandardStreams = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }
}

tasks.register("testAllModules") {
    description = "Run unit tests for all modules."
    group = "verification"

    subprojects.forEach { project ->
        project.tasks.configureEach {
            if (name == "testDebugUnitTest") {
                this@register.dependsOn(this)
            }
        }
    }
}
