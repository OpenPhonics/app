plugins {
    alias(libs.plugins.openphonics.android.application)
    alias(libs.plugins.openphonics.android.hilt)
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
        debug {
            applicationIdSuffix = ".debug"
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
    implementation(projects.core.data)
    implementation(projects.sync)

    implementation(projects.feature.home)
    implementation(projects.feature.login)
    implementation(projects.feature.skill)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.navigation)
    implementation(libs.lifecycle.runtime.compose)


    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.android.test)
    debugImplementation(libs.bundles.composeDebug)
}