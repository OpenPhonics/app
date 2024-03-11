plugins {
    alias(libs.plugins.openphonics.android.feature)
}

android {
    namespace = "com.advaitvedant.lesson"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)
    api(projects.core.soundmanager)
    api(projects.core.speechtotext)
}