import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
}

group = "com.github.klee0kai.cloud"

android {
    namespace = group.toString()
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = group.toString()
        minSdk = libs.versions.android.minSdk.get().toInt()
        versionCode = libs.versions.cloud.analytics.code.get().toInt()
        versionName = libs.versions.cloud.analytics.name.get().toString()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

kotlin {
    androidTarget {

    }

    jvm("desktop") {

    }

    wasmJs {
        outputModuleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "bundle.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    cssSupport { enabled = true }
                    mode = KotlinWebpackConfig.Mode.DEVELOPMENT
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }

        }

        binaries.executable()
    }

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.12.0")
                implementation("androidx.compose.ui:ui:1.5.0")
                implementation("androidx.compose.material3:material3:1.2.0")


                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)
            }
        }


        val commonMain by getting {
            dependencies {
                implementation(projects.shared)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.material3AdaptiveNavigationSuite)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.runtimeCompose)
                implementation(libs.stone.kotlin)
                implementation(libs.kermit)
                implementation("org.jetbrains.kotlinx:atomicfu:0.23.2")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutinesSwing)

                implementation(compose.preview)
                implementation(compose.components.uiToolingPreview)
            }
        }
    }
}



dependencies {
    ksp(libs.stone.ksp)
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.github.klee0kai.cloud.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = group.toString()
            packageVersion = libs.versions.cloud.analytics.name.get().toString()
        }
    }
}

val wasmArtifactsJar by tasks.register<Jar>(name = "wasmJsBrowserProductionJar") {
    group = "build"
    description = "Package WebAssembly production artifacts into a JAR"

    archiveBaseName.set("wasm-artifacts")
    archiveClassifier.set("prod")
    archiveVersion.set(libs.versions.cloud.analytics.name.get())

    dependsOn("wasmJsBrowserDistribution")

    from(layout.buildDirectory.dir("dist/wasmJs/productionExecutable")) {
        include("**/*")
    }
}

artifacts {
    add("archives", wasmArtifactsJar)
}
