import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import com.advaitvedant.openphonics.config.ProjectConfig
import com.advaitvedant.openphonics.config.configureAndroidApplication
import com.advaitvedant.openphonics.config.configureAndroidCompose


class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<ApplicationExtension> {
                configureAndroidApplication(this)
                configureAndroidCompose(this)
                defaultConfig.applicationId = ProjectConfig.APPLICATION_ID
                defaultConfig.targetSdk = ProjectConfig.TARGET_SDK
                defaultConfig.versionName = ProjectConfig.VERSION_NAME
                defaultConfig.versionCode = ProjectConfig.VERSION_CODE
            }
        }
    }
}
