package com.advaitvedant.openphonics.config

import com.advaitvedant.openphonics.ext.lib
import com.advaitvedant.openphonics.ext.testImplementation
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidLibrary(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = ProjectConfig.COMPILE_SDK
        defaultConfig {
            minSdk = ProjectConfig.MIN_SDK
            testInstrumentationRunner = ProjectConfig.TEST_INSTRUMENTATION_RUNNER
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        dependencies {
            testImplementation(lib("junit"))
        }
    }
}