package com.github.klee0kai.cloud.di.dependencies

import com.github.klee0kai.stone.annotations.dependencies.Dependencies

@Dependencies
interface AppComponentProviders :
    PresentersDependencies,
    InteractorsDependencies,
    RepositoriesDependencies
