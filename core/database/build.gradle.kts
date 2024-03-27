plugins {
    alias(libs.plugins.openphonics.android.library)
    alias(libs.plugins.openphonics.android.hilt)
}

android {
    namespace = "com.advaitvedant.database"
}

dependencies {
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}