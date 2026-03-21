package com.github.klee0kai.cloud.core.di.modules

import com.github.klee0kai.stone.annotations.component.Init
import com.github.klee0kai.stone.annotations.component.ModuleOriginFactory

interface CoreJvmModules {

    /* get module */
    fun interactorsJvmModule(): InteractorsJvmCoreModule
    fun servicesJvmModule(): ServicesJvmCoreModule

    /* get origin factories */
    @ModuleOriginFactory
    fun interactorsJvmModuleFactory(): InteractorsJvmCoreModule

    @ModuleOriginFactory
    fun servicesJvmModuleFactory(): ServicesJvmCoreModule

    /* override */

    @Init
    fun initInteractorsJvmModule(services: InteractorsJvmCoreModule)

    @Init
    fun initJvmServicesModule(services: ServicesJvmCoreModule)

}