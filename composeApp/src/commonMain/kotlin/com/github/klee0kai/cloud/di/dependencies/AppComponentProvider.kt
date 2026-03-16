package com.github.klee0kai.cloud.di.dependencies

import com.github.klee0kai.cloud.core.di.providers.InteractorsProvider
import com.github.klee0kai.cloud.core.di.providers.PresentersProvider
import com.github.klee0kai.cloud.core.di.providers.ServicesProvider
import com.github.klee0kai.stone.annotations.dependencies.Dependencies

@Dependencies
interface AppComponentProvider :
    PresentersProvider,
    InteractorsProvider,
    ServicesProvider
