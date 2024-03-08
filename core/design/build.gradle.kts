plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.openphonics.android.library.compose)
}

android {
    namespace = "com.advaitvedant.design"
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.androidx.compose.icons)
}