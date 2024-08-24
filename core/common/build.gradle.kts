plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.hilt)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "template.core.common"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(libs.timber)
}
