package com.advaitvedant.openphonics

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun Project.lib(library: String) = libs.findLibrary(library).get()
fun Project.bundle(bundle: String) = libs.findBundle(bundle).get()



fun Project.version(version: String): String = libs.findVersion(version).get().toString()
