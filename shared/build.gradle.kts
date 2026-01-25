plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

group = "com.github.klee0kai.cloud.shared"

kotlin {
    androidTarget {

    }

    jvm()

    wasmJs {
        browser {

        }
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // put your Multiplatform dependencies here
                implementation(libs.kermit)
                implementation(libs.stone.kotlin)
                implementation(libs.jetbrains.koog)
                implementation(libs.kotlinpoet)

                implementation(libs.bundles.kotlin)
                implementation(libs.bundles.ktor.common)
                implementation(libs.bundles.ktor.client)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.gradle.tooling)
                implementation(libs.tasktree)
                implementation(libs.bundles.ktor.server)
                implementation(libs.ktor.client.okhttp)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }

        val wasmJsMain by getting {
            dependencies {
                implementation(libs.ktor.client.js)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.test)
            }
        }
    }
}

android {
    namespace = group.toString()
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        version = libs.versions.cloud.analytics.name.get()

    }
}


dependencies {
    ksp(libs.stone.ksp)
}
