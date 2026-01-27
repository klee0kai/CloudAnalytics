import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
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



    js {
        outputModuleName = "composeAppJs"
        browser {
            commonWebpackConfig {
                outputFileName = "bundle.js"
            }
        }
        binaries.executable()
    }

    wasmJs {
        outputModuleName = "composeAppWasm"
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
        val commonMain by getting {
            dependencies {
                implementation(projects.shared)
                implementation(libs.kotlinx.atomicfu)

                implementation(libs.bundles.compose)

                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.runtimeCompose)
                implementation(libs.stone.kotlin)
                implementation(libs.kermit)

                implementation(libs.compose.kuiver)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutinesSwing)
                implementation(libs.compose.tooling)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.activity.compose)
                implementation(libs.compose.tooling)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
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

val wasmArchives by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
    }
}

artifacts {
    add("wasmArchives", wasmArtifactsJar)
}
