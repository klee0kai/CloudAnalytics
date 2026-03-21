plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.rpc)
    alias(libs.plugins.kotlin.serialization)
}

group = "com.github.klee0kai.cloud.shared"

kotlin {
    androidTarget {

    }

    jvm()

    js {
        browser { }
        binaries.executable()
    }

    wasmJs {
        browser { }
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.bundles.kotlin)
                api(libs.bundles.ktor.common)
                api(libs.bundles.ktor.client)
                api(libs.bundles.rpc.client)
                api(libs.bundles.multiplatform.settings)

                // put your Multiplatform dependencies here
                api(libs.kotlinx.atomicfu)
                api(libs.kermit)
                api(libs.stone.kotlin)

                api(libs.kotlin.poet)

                api(libs.koson)
            }
        }

        val jvmMain by getting {
            dependencies {
                api(libs.bundles.ktor.server)
                api(libs.bundles.rpc.server)
                api(libs.bundles.ktlint)

                api(libs.jetbrains.koog)
                api(libs.gradle.tooling)
                api(libs.tasktree)
                api(libs.ktor.client.okhttp)
                api(libs.ktor.client.cio)

                api(libs.kotlin.ksp)
                api(libs.kotlin.compiler)
                api(libs.kotlin.poet.ksp)
            }
        }

        val androidMain by getting {
            dependencies {
                api(libs.jetbrains.koog)
                api(libs.ktor.client.okhttp)
            }
        }

        val wasmJsMain by getting {
            dependencies {
                api(libs.ktor.client.js)
            }
        }
        val commonTest by getting {
            dependencies {
                api(libs.kotlin.test)
            }
        }

        val jvmTest by getting {
            dependencies {
                api(libs.kotlinx.coroutines.test)
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
