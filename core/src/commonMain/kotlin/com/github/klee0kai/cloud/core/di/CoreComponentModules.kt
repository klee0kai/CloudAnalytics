package com.github.klee0kai.cloud.core.di

import com.github.klee0kai.cloud.core.di.modules.*
import com.github.klee0kai.stone.annotations.component.Init
import com.github.klee0kai.stone.annotations.component.ModuleOriginFactory

interface CoreComponentModules {

    /* get module */
    fun presentersModule(): PresentersModule
    fun interactorsModule(): InteractorsModule
    fun servicesModule(): ServicesModule
    fun dataModule(): DataModule
    fun coroutineModule(): CoroutineScopeModule

    /* get origin factories */
    @ModuleOriginFactory
    fun presentersModuleFactory(): PresentersModule

    @ModuleOriginFactory
    fun interactorsModuleFactory(): InteractorsModule

    @ModuleOriginFactory
    fun servicesModuleFactory(): ServicesModule

    @ModuleOriginFactory
    fun dataModuleFactory(): DataModule

    @ModuleOriginFactory
    fun coroutineModuleFactory(): CoroutineScopeModule

    /* override */

    @Init
    fun initPresenterModule(presentersModule: PresentersModule)

    @Init
    fun initInteractorsModule(services: InteractorsModule)

    @Init
    fun initServicesModule(services: ServicesModule)

    @Init
    fun initDataModule(data: DataModule)

    @Init
    fun initCoroutineModule(coroutine: CoroutineScopeModule)


}