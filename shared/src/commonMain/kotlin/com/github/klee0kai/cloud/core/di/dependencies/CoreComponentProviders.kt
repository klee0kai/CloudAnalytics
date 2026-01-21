package com.github.klee0kai.cloud.core.di.dependencies

import com.github.klee0kai.stone.annotations.dependencies.Dependencies

@Dependencies
interface CoreComponentProviders :
    PresentersDependencies,
    InteractorsDependencies,
    ServicesDependencies
