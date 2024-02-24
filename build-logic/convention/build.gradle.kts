import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.advaitvedant.openphonics.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}
dependencies {
    compileOnly(libs.bundles.conventionPlugins)
}
tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}


gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "openphonics.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "openphonics.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose"){
            id = "openphonics.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "openphonics.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidDatastore"){
            id = "openphonics.android.datastore"
            implementationClass = "AndroidDatastoreConventionPlugin"
        }
        register("androidFeature"){
            id = "openphonics.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}