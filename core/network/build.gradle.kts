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
    implementation(projects.core.session)
    implementation(libs.bundles.retrofit)
}