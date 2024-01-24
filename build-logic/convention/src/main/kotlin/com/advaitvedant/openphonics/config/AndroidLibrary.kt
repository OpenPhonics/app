package com.advaitvedant.openphonics.config
import com.advaitvedant.openphonics.ProjectConfig
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion

internal fun configureAndroidLibrary(
    commonExtension: CommonExtension<*, *, *, *, *>,
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
    }
}