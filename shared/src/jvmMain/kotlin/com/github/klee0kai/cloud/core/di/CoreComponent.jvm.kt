package com.github.klee0kai.cloud.core.di

import com.github.klee0kai.cloud.core.di.modules.InteractorsModuleJvm
import com.github.klee0kai.cloud.core.di.modules.ServicesModuleJvm

actual val DI: CoreComponent = CoreComponentStoneComponent().apply {
    initJvmModules()
}


fun CoreComponent.initJvmModules() {
    initServicesModule(ServicesModuleJvm())
    initInteractorsModule(InteractorsModuleJvm())
}