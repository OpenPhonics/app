plugins {
    alias(libs.plugins.openphonics.android.application)
}

android {
    namespace = "com.advaitvedant.openphonics"
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.core.design)

    implementation(projects.feature.home)
    implementation(projects.feature.explore)
    implementation(projects.feature.bookmarks)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.bundles.compose)
}