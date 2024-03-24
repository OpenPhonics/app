plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.openphonics.android.library.compose)
}

android {
    namespace = "com.advaitvedant.ui"
}

dependencies {

    implementation(libs.bundles.compose)
//    api(projects.core.model)
    api(projects.core.design)
    debugImplementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)
}