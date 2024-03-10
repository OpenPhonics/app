pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "openphonics"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:design")
include(":feature:home")
include(":feature:explore")
include(":feature:bookmarks")
include(":feature:login")
include(":feature:signup")
include(":feature:settings")
include(":feature:lesson")
include(":core:ui")
include(":core:model")
include(":core:data")
include(":core:session")
include(":core:datastore-proto")
include(":core:datastore")
include(":core:common")
