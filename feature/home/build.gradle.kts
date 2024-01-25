plugins {
    alias(libs.plugins.openphonics.android.library.compose)
}

android {
    namespace = "com.advaitvedant.home"
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.bundles.navigation)

    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.android.test)

    debugImplementation(libs.bundles.compose.debug)
}