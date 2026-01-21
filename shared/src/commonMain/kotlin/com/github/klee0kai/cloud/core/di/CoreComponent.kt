package com.github.klee0kai.cloud.core.di

import com.github.klee0kai.cloud.core.di.dependencies.CoreComponentProviders
import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.stone.annotations.component.Component


expect val DI :CoreComponent

@Component(
    identifiers = [
        GradleProjectId::class,
    ],
)
interface CoreComponent : CoreComponentProviders, CoreComponentModules