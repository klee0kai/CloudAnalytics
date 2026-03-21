package com.github.klee0kai.cloud.di.modules

import com.github.klee0kai.cloud.core.di.modules.InteractorsCoreModule
import com.github.klee0kai.cloud.core.di.modules.PresentersCoreModule
import com.github.klee0kai.cloud.core.di.modules.ServicesCoreModule
import com.github.klee0kai.stone.annotations.component.Init
import com.github.klee0kai.stone.annotations.component.ModuleOriginFactory

interface ComposeModules {

    /* get module */
    fun presenters(): PresentersCoreModule
    fun interactors(): InteractorsCoreModule
    fun repositories(): ServicesCoreModule

    /* get origin factories */
    @ModuleOriginFactory
    fun presentersFactory(): PresentersCoreModule

    @ModuleOriginFactory
    fun interactorsFactory(): InteractorsCoreModule

    @ModuleOriginFactory
    fun repositoriesFactory(): ServicesCoreModule

    /* override */

    @Init
    fun initPresenterModule(presentersCoreModule: PresentersCoreModule)


}