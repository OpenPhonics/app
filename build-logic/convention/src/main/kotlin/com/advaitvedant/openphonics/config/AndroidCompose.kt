package com.advaitvedant.openphonics.config

import com.advaitvedant.openphonics.androidTestImplementation
import com.advaitvedant.openphonics.implementation
import com.advaitvedant.openphonics.lib
import com.advaitvedant.openphonics.version
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = version("composeVersion")
        }
        dependencies {
            implementation(platform(lib("compose-bom")))
            androidTestImplementation(platform(lib("compose-bom")))
//            implementation(lib("compose-window"))
//            implementation(lib("compose-material3"))
//            implementation(lib("compose-ui"))
//            implementation(lib("compose-ui-graphics"))
//            implementation(lib("compose-ui-tooling-preview"))
//            implementation(lib("compose-runtime"))
//            implementation(lib("compose-foundation"))
//            implementation(bundle("compose"))
//            implementation(bundle("android-test"))
//            debugImplementation(bundle("compose-debug"))

        }
    }
}