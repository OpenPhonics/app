plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.openphonics.android.hilt)
}

android {
    namespace = "com.advaitevdant.data"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.session)
}