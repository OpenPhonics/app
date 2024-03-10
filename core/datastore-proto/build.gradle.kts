
import com.advaitvedant.openphonics.config.configureFlavors
import com.android.build.api.dsl.LibraryExtension

plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.protobuf)

}
extensions.configure<LibraryExtension> {
    configureFlavors(this)
}
android {
    namespace = "com.advaitvedant.datastore.proto"
}

// Setup protobuf configuration, generating lite Java and Kotlin classes
protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}
androidComponents.beforeVariants {
//    android.sourceSets.register()
    println("THENAME " + it.name)
    android.sourceSets.register(it.name) {
        val buildDir = layout.buildDirectory.get().asFile
        java.srcDir(buildDir.resolve("generated/source/proto/${it.name}/java"))
        kotlin.srcDir(buildDir.resolve("generated/source/proto/${it.name}/kotlin"))
    }
}


dependencies {
    api(libs.protobuf.kotlin.lite)
}