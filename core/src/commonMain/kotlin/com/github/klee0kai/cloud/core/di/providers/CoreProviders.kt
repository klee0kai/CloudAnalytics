package com.github.klee0kai.cloud.core.di.providers

import com.github.klee0kai.stone.annotations.dependencies.Dependencies

@Dependencies
interface CoreProviders :
    PresentersCoreProvider,
    InteractorsCoreProvider,
    ServicesCoreProvider,
    DataCoreProvider,
    CoroutineCoreProvider
