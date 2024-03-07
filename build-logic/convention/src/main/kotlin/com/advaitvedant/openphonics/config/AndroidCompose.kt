package com.advaitvedant.openphonics.config


import com.advaitvedant.openphonics.ext.androidTestImplementation
import com.advaitvedant.openphonics.ext.implementation
import com.advaitvedant.openphonics.ext.lib
import com.advaitvedant.openphonics.ext.version
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = version("composeVersion")
        }
        dependencies {
            implementation(platform(lib("androidx-compose-bom")))
            androidTestImplementation(platform(lib("androidx-compose-bom")))
        }
    }
}