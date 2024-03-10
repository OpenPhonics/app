plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.openphonics.android.hilt)
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    namespace = "com.advaitvedant.datastore"
}

dependencies {
    api(libs.androidx.dataStore.core)
    api(projects.core.datastoreProto)
    api(projects.core.common)
    api(projects.core.model)
}