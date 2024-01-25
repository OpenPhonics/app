import com.advaitvedant.openphonics.config.configureAndroidCompose
import com.advaitvedant.openphonics.config.configureAndroidLibrary
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<LibraryExtension> {
                configureAndroidLibrary(this)
                configureAndroidCompose(this)
            }
        }
    }
}