plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.openphonics.android.library.compose)
}

android {
    namespace = "com.advaitvedant.ui"
}

dependencies {

    implementation(libs.bundles.compose)
}