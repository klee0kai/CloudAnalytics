package com.github.klee0kai.cloud.core.di

import com.github.klee0kai.cloud.core.di.modules.InteractorsModule
import com.github.klee0kai.cloud.core.di.modules.PresentersModule
import com.github.klee0kai.cloud.core.di.modules.ServicesModule
import com.github.klee0kai.stone.annotations.component.Init
import com.github.klee0kai.stone.annotations.component.ModuleOriginFactory

interface CoreComponentModules {

    /* get module */
    fun presenters(): PresentersModule
    fun interactors(): InteractorsModule
    fun services(): ServicesModule

    /* get origin factories */
    @ModuleOriginFactory
    fun presentersFactory(): PresentersModule

    @ModuleOriginFactory
    fun interactorsFactory(): InteractorsModule

    @ModuleOriginFactory
    fun servicesFactory(): ServicesModule

    /* override */

    @Init
    fun initPresenterModule(presentersModule: PresentersModule)


}