package com.github.klee0kai.cloud.core.di

import com.github.klee0kai.cloud.core.di.providers.CoreProviders
import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.di.modules.CoreModules
import com.github.klee0kai.stone.annotations.component.Component


expect val CoreDI :CoreComponent

@Component(
    identifiers = [
        GradleProjectId::class,
    ],
)
interface CoreComponent : CoreProviders, CoreModules