plugins {
    alias(libs.plugins.openphonics.android.library.compose)
    alias(libs.plugins.openphonics.android.library)

}

android {
    namespace = "com.advaitvedant.design"
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.compose.icons)

    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.android.test)
    debugImplementation(libs.bundles.composeDebug)
}