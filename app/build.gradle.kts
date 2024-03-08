plugins {
    alias(libs.plugins.openphonics.android.application)
    alias(libs.plugins.openphonics.android.hilt)
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
    implementation(projects.feature.login)
    implementation(projects.feature.signup)
    implementation(projects.feature.settings)
    implementation(projects.feature.lesson)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.bundles.compose)
}