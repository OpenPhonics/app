plugins {
    alias(libs.plugins.openphonics.android.feature)

}

android {
    namespace = "com.advaitvedant.home"

}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)

    implementation(projects.core.design)
    implementation(projects.core.ui)
}