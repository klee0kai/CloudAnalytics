package com.github.klee0kai.cloud.core.di.modules.remote

import com.github.klee0kai.cloud.core.di.CoreDI
import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.di.modules.InteractorsCoreModule
import com.github.klee0kai.cloud.core.gradle.GradleProjectInteractor
import io.ktor.client.request.*
import kotlinx.rpc.krpc.ktor.client.rpc
import kotlinx.rpc.withService

open class InteractorsRemoteCoreModule : InteractorsCoreModule {

    val settings get() = CoreDI.appSettings()
    val networkEngine get() = CoreDI.networkClientEngine()

    override suspend fun gradleProjectInteractor(
        gradleProjectId: GradleProjectId,
    ): GradleProjectInteractor {
        val engine = CoreDI.networkClientEngine()
        val address = "${settings().remoteServerAddress()}/gradleProjectService/${gradleProjectId.projectPath}"
        val client = engine().rpc {
            url(address)
        }
        return client.withService<GradleProjectInteractor>()
    }


}