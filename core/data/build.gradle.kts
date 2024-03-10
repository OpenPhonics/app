plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.openphonics.android.hilt)
}

android {
    namespace = "com.advaitevdant.data"
}

dependencies {
    api(projects.core.model)
    api(projects.core.session)
    api(projects.core.datastore)
}