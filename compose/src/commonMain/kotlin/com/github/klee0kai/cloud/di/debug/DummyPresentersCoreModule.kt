package com.github.klee0kai.cloud.di.debug

import com.github.klee0kai.cloud.core.di.modules.PresentersCoreModule

open class DummyPresentersCoreModule(
    origin: PresentersCoreModule,
) : PresentersCoreModule by origin {


}