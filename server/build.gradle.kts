plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.ksp)
    kotlin("kapt")
    application
}

group = "com.github.klee0kai.cloud"
version = libs.versions.cloud.analytics.name.get()

application {
    mainClass.set("com.github.klee0kai.cloud.ApplicationKt")
    
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(project(path = ":composeApp", configuration = "wasmArchives"))

    implementation(projects.shared)

    implementation(libs.logback)
    implementation(libs.bundles.ktor.server)
    implementation(libs.bundles.ktor.client)

    implementation(libs.kermit)

    testImplementation(libs.ktor.server.testhost)
    testImplementation(libs.kotlin.testJunit)
}