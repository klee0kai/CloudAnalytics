package com.github.klee0kai.cloud.di.dependencies

import com.github.klee0kai.cloud.core.di.providers.InteractorsCoreProvider
import com.github.klee0kai.cloud.core.di.providers.PresentersCoreProvider
import com.github.klee0kai.cloud.core.di.providers.ServicesCoreProvider
import com.github.klee0kai.stone.annotations.dependencies.Dependencies

@Dependencies
interface ComposeProviders :
    PresentersCoreProvider,
    InteractorsCoreProvider,
    ServicesCoreProvider
