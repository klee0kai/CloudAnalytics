plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ksp)
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
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
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
