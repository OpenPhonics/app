plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.openphonics.android.hilt)
}

android {
    namespace = "com.advaitvedant.sharedpref"
}

dependencies {

    implementation(libs.security)
    testImplementation(libs.bundles.test)
}