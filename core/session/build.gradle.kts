plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.openphonics.android.hilt)
}

android {
    namespace = "com.advaitevdant.session"
}

dependencies {
    implementation(libs.androidx.security)

}