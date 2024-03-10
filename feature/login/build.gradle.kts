plugins {
    alias(libs.plugins.openphonics.android.feature)
}

android {
    namespace = "com.advaitvedant.login"
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.design)
    implementation(projects.core.data)
}