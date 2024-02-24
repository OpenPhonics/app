import com.advaitvedant.openphonics.bundle
import com.advaitvedant.openphonics.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDatastoreConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.protobuf")
            }
            dependencies {
                implementation(bundle("datastore"))

            }
        }
    }
}