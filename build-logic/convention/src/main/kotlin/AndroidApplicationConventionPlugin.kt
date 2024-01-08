import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.dsl.ApplicationExtension
import template.configureFlavors
import template.configureKotlinAndroid
import template.configurePrintApksTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.findVersion("compile-sdk").get().toString().toInt()
                configureFlavors(this)
            }
            extensions.configure<ApplicationAndroidComponentsExtension> {
                configurePrintApksTask(this)
            }
        }
    }

}
