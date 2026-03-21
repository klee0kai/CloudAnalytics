package com.github.klee0kai.cloud.core.di

import com.github.klee0kai.cloud.core.di.ext.InteractorsCoreModuleJvmExt
import com.github.klee0kai.cloud.core.di.ext.ServicesCoreModuleJvmExt
import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.di.modules.CoreJvmModules
import com.github.klee0kai.cloud.core.di.providers.CoreJvmProviders
import com.github.klee0kai.stone.annotations.component.Component
import com.github.klee0kai.stone.annotations.component.ExtendOf

val CoreJvmDI: CoreJvmComponent = CoreJvmComponentStoneComponent().apply {
    initJvmModules()
}

actual val CoreDI: CoreComponent get() = CoreJvmDI

fun CoreComponent.initJvmModules() {
    initServicesModule(ServicesCoreModuleJvmExt())
    initInteractorsModule(InteractorsCoreModuleJvmExt())
}


@Component(
    identifiers = [
        GradleProjectId::class,
    ],
)
interface CoreJvmComponent : CoreComponent, CoreJvmProviders, CoreJvmModules {

    @ExtendOf
    fun ext(component: CoreComponent)

}