package com.github.klee0kai.cloud.di

import com.github.klee0kai.cloud.di.modules.InteractorsModule
import com.github.klee0kai.cloud.di.modules.PresentersModule
import com.github.klee0kai.cloud.di.modules.RepositoriesModule
import com.github.klee0kai.stone.annotations.component.Init
import com.github.klee0kai.stone.annotations.component.ModuleOriginFactory

interface AppComponentModules {

    /* get module */
    fun presenters(): PresentersModule
    fun interactors(): InteractorsModule
    fun repositories(): RepositoriesModule

    /* get origin factories */
    @ModuleOriginFactory
    fun presentersFactory(): PresentersModule

    @ModuleOriginFactory
    fun interactorsFactory(): InteractorsModule

    @ModuleOriginFactory
    fun repositoriesFactory(): RepositoriesModule

    /* override */

    @Init
    fun initPresenterModule(presentersModule: PresentersModule)


}