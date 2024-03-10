

plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.protobuf)
}
android {
    namespace = "com.advaitvedant.datastore.proto"
    sourceSets {
        all {
            val buildDir = layout.buildDirectory.get().asFile
            java.srcDir(buildDir.resolve("generated/source/proto/${name}/java"))
            kotlin.srcDir(buildDir.resolve("generated/source/proto/${name}/kotlin"))
        }
    }
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
//androidComponents.beforeVariants {
////    android.sourceSets.register()
//    println("THENAME " + it.name)
//    android.sourceSets.register(it.name) {
//        val buildDir = layout.buildDirectory.get().asFile
//        java.srcDir(buildDir.resolve("generated/source/proto/${it.name}/java"))
//        kotlin.srcDir(buildDir.resolve("generated/source/proto/${it.name}/kotlin"))
//    }
//}
//

dependencies {
    api(libs.protobuf.kotlin.lite)
}