plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.openphonics.android.hilt)
}

android {
    namespace = "com.advaitvedant.soundmanager"
}

dependencies {
    api(projects.core.common)
}