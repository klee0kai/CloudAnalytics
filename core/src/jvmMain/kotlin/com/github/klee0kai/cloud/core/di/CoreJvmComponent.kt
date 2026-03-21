package com.github.klee0kai.cloud.core.di

import com.github.klee0kai.cloud.core.di.ext.InteractorsCoreModuleJvmExt
import com.github.klee0kai.cloud.core.di.ext.ServicesCoreModuleJvmExt
import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.di.modules.CoreJvmModules
import com.github.klee0kai.cloud.core.di.providers.CoreJvmProviders
import com.github.klee0kai.stone.annotations.component.Component
import com.github.klee0kai.stone.annotations.component.ExtendOf

val CoreJvmComponentDI: CoreJvmComponent = CoreComponentJvmComponentStone().apply {
    initJvmModules()
}

actual val CoreComponentDI: Core get() = CoreJvmComponentDI

fun Core.initJvmModules() {
    initServicesModule(ServicesCoreModuleJvmExt())
    initInteractorsModule(InteractorsCoreModuleJvmExt())
}


@Component(
    identifiers = [
        GradleProjectId::class,
    ],
)
interface CoreJvmComponent : Core, CoreJvmProviders, CoreJvmModules {

    @ExtendOf
    fun ext(component: Core)

}