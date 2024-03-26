plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.openphonics.android.hilt)
    id("kotlinx-serialization")

}

android {
    namespace = "com.advaitvedant.network"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(projects.core.model)
    api(projects.core.session)
    implementation(libs.bundles.retrofit)
}