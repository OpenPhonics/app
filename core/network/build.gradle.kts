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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0-RC2")
    testImplementation("androidx.test:core:1.6.0-alpha04")
    implementation(projects.core.sharedpref)
    implementation(libs.bundles.retrofit)
    implementation(libs.androidx.monitor)
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.android.test)
}