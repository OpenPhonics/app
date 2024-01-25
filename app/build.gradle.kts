plugins {
    alias(libs.plugins.openphonics.android.application)
}

android {
    namespace = "com.advaitvedant.openphonics"
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {

    implementation(projects.core.design)

    implementation(projects.feature.home)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.navigation)

    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.android.test)

    debugImplementation(libs.bundles.compose.debug)
}