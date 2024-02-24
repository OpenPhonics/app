plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.openphonics.android.hilt)
}

android {
    namespace = "com.advaitvedant.sync"
}

dependencies {
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.runtime.compose)
    testImplementation(libs.bundles.test)
    implementation(libs.hilt.work)
    ksp(libs.hilt.ext.compiler)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(projects.core.data)
    implementation(projects.core.datastore)
    implementation(projects.core.common)
}