plugins {
    alias(libs.plugins.openphonics.android.feature)

}

android {
    namespace = "com.advaitvedant.home"

}

dependencies {
    implementation(projects.core.model)
}