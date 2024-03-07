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
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
}