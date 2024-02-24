plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.openphonics.android.hilt)
}

android {
    namespace = "com.advaitvedant.data"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.sharedpref)
    implementation(projects.core.datastore)
    implementation(libs.retrofit)
    implementation(libs.androidx.junit.ktx)
    implementation(projects.core.database)
    implementation(projects.core.common)
    androidTestImplementation(libs.bundles.android.test)
    testImplementation(libs.bundles.test)
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0-RC2")
//    testImplementation("androidx.test:core:1.6.0-alpha04")
//    testImplementation("org.mockito:mockito-core:5.10.0")
//    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
//    testImplementation("io.mockk:mockk:1.13.9")
}