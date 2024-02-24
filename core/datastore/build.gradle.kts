plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.openphonics.android.hilt)
    alias(libs.plugins.openphonics.android.datastore)
}

android {
    namespace = "com.advaitvedant.datastore"
//    defaultConfig {
//        consumerProguardFiles("consumer-rules.pro")
//    }
}

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

dependencies {
    implementation(projects.core.common)
    testImplementation(libs.bundles.test)
}