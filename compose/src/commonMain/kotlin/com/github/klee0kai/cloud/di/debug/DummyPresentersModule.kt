package com.github.klee0kai.cloud.di.debug

import com.github.klee0kai.cloud.core.di.modules.PresentersModule

open class DummyPresentersModule(
    origin: PresentersModule,
) : PresentersModule by origin {


}