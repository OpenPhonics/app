import com.advaitvedant.openphonics.implementation
import com.advaitvedant.openphonics.ksp
import com.advaitvedant.openphonics.lib
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }

            dependencies {
                implementation(lib("hilt-android"))
                ksp(lib("hilt-compiler"))
            }
        }
    }
}