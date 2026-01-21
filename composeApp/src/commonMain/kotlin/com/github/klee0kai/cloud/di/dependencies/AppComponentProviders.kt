package com.github.klee0kai.cloud.di.dependencies

import com.github.klee0kai.cloud.core.di.dependencies.InteractorsDependencies
import com.github.klee0kai.cloud.core.di.dependencies.PresentersDependencies
import com.github.klee0kai.cloud.core.di.dependencies.ServicesDependencies
import com.github.klee0kai.stone.annotations.dependencies.Dependencies

@Dependencies
interface AppComponentProviders :
    PresentersDependencies,
    InteractorsDependencies,
    ServicesDependencies
