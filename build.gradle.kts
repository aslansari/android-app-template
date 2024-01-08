// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinter) apply false
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.com.android.test) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.ksp) apply false
}

apply(from = "buildscripts/githooks.gradle")
apply(from = "buildscripts/setup.gradle")

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

afterEvaluate {
    // We install the hook at the first occasion
    tasks.named("clean") {
        dependsOn(":installGitHooks")
    }
}

// Detekt Config
apply(plugin = "io.gitlab.arturbosch.detekt")

detekt {
    config = files("${rootProject.projectDir}/config/detekt/detekt.yml")
    baseline = file("${rootProject.projectDir}/config/detekt/detekt.xml")

    reports {
        html.required.set(true)
        xml.required.set(true)
        txt.required.set(true)
    }
}

tasks {
    /**
     * The detektAll tasks enables parallel usage for detekt so if this project
     * expands to multi module support, detekt can continue to run quickly.
     *
     * https://proandroiddev.com/how-to-use-detekt-in-a-multi-module-android-project-6781937fbef2
     */
    @Suppress("UnusedPrivateMember")
    val detektAll by registering(io.gitlab.arturbosch.detekt.Detekt::class) {
        parallel = true
        setSource(files(projectDir))
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**")
        exclude("**/build/**")
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        buildUponDefaultConfig = false
    }
}
