plugins {
    alias(libs.plugins.openphonics.android.feature)
}

android {
    namespace = "com.advaitvedant.signup"
}

dependencies {
    implementation(projects.core.ui)
}