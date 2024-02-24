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
include(":core:network")
include(":core:data")
include(":core:datastore")
include(":core:sharedpref")
include(":feature:login")
include(":sync")
include(":core:common")
include(":core:database")
include(":feature:skill")
