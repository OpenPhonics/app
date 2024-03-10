plugins {
    alias(libs.plugins.openphonics.android.feature)
}

android {
    namespace = "com.advaitvedant.settings"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.design)
}