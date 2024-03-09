plugins {
    alias(libs.plugins.openphonics.android.feature)
}

android {
    namespace = "com.advaitvedant.login"
}

dependencies {
    implementation(projects.core.ui)
}