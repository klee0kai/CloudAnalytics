package com.github.klee0kai.cloud.di

import com.github.klee0kai.cloud.core.di.CoreComponent
import com.github.klee0kai.cloud.di.dependencies.ComposeProviders
import com.github.klee0kai.cloud.di.modules.ComposeModules
import com.github.klee0kai.stone.annotations.component.Component

@Component(
    identifiers = [

    ],
)
interface ComposeComponent : CoreComponent, ComposeProviders, ComposeModules {

    fun ext(coreComponent: CoreComponent)

}