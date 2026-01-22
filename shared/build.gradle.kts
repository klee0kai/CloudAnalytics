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
                implementation(libs.stone.kotlin)
                implementation(libs.jetbrains.koog)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("org.gradle:gradle-tooling-api:9.1.0")
            }
        }


        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
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
