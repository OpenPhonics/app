
import com.advaitvedant.openphonics.config.configureAndroidCompose
import com.advaitvedant.openphonics.config.configureAndroidLibrary
import com.advaitvedant.openphonics.ext.bundle
import com.advaitvedant.openphonics.ext.implementation
import com.advaitvedant.openphonics.ext.ksp
import com.advaitvedant.openphonics.ext.lib
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }
            extensions.configure<LibraryExtension> {
                configureAndroidLibrary(this)
                configureAndroidCompose(this)
            }
            dependencies {
                implementation(bundle("compose"))
                implementation(lib("androidx-navigation-compose"))
                implementation(lib("androidx-hilt-navigation-compose"))
                implementation(lib("hilt-android"))
                ksp(lib("hilt-compiler"))
//                implementation(project(":core:design"))
//                implementation(project(":core:data"))
                implementation(lib("lifecycle-runtime-compose"))
                implementation(lib("lifecycle-viewModel-compose"))
            }
        }
    }
}
