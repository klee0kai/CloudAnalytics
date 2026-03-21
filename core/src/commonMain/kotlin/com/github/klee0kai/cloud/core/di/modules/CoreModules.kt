package com.github.klee0kai.cloud.core.di.modules

import com.github.klee0kai.stone.annotations.component.Init
import com.github.klee0kai.stone.annotations.component.ModuleOriginFactory

interface CoreModules {

    /* get module */
    fun presentersModule(): PresentersCoreModule
    fun interactorsModule(): InteractorsCoreModule
    fun servicesModule(): ServicesCoreModule
    fun dataModule(): DataCoreModule
    fun coroutineModule(): CoroutineCoreModule

    /* get origin factories */
    @ModuleOriginFactory
    fun presentersModuleFactory(): PresentersCoreModule

    @ModuleOriginFactory
    fun interactorsModuleFactory(): InteractorsCoreModule

    @ModuleOriginFactory
    fun servicesModuleFactory(): ServicesCoreModule

    @ModuleOriginFactory
    fun dataModuleFactory(): DataCoreModule

    @ModuleOriginFactory
    fun coroutineModuleFactory(): CoroutineCoreModule

    /* override */

    @Init
    fun initPresenterModule(presentersCoreModule: PresentersCoreModule)

    @Init
    fun initInteractorsModule(services: InteractorsCoreModule)

    @Init
    fun initServicesModule(services: ServicesCoreModule)

    @Init
    fun initDataModule(data: DataCoreModule)

    @Init
    fun initCoroutineModule(coroutine: CoroutineCoreModule)


}