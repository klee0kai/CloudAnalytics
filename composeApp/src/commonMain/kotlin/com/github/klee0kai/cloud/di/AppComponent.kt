package com.github.klee0kai.cloud.di

import com.github.klee0kai.cloud.di.dependencies.AppComponentProviders
import com.github.klee0kai.cloud.di.wrap.AppWrappersStone
import com.github.klee0kai.stone.KotlinWrappersStone
import com.github.klee0kai.stone.annotations.component.Component

//var DI: AppComponent = initAppComponent()
//    private set

@Component(
    identifiers = [
    ],
    wrapperProviders = [
        KotlinWrappersStone::class,
        AppWrappersStone::class,
    ],
)
interface AppComponent : AppComponentProviders, AppComponentModules {


}




//private fun initAppComponent(
//    forceConnectFeatures: Boolean = false,
//) = AppComponentStoneComponent().apply {
//
//}
