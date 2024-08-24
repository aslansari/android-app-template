plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.library.compose)
}

android {
    namespace = "template.core.designsystem"
}

dependencies {
    api(libs.compose.foundation)
    api(libs.compose.foundation.layout)
    api(libs.compose.material.icons.extended)
    api(libs.compose.material3)
    api(libs.compose.runtime)
    api(libs.compose.ui.util)
    api(libs.compose.ui.tooling)

    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt.compose)

}

