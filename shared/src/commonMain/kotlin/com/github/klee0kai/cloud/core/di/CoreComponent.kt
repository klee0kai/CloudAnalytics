package com.github.klee0kai.cloud.core.di

import com.github.klee0kai.cloud.core.di.dependencies.CoreComponentProviders
import com.github.klee0kai.cloud.core.di.identifier.GradleProject
import com.github.klee0kai.stone.annotations.component.Component

@Component(
    identifiers = [
        GradleProject::class,
    ],
)
interface CoreComponent : CoreComponentProviders, CoreComponentModules