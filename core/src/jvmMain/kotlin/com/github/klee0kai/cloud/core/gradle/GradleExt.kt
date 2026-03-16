package com.github.klee0kai.cloud.core.gradle

import kotlinx.coroutines.suspendCancellableCoroutine
import org.gradle.tooling.BuildLauncher
import org.gradle.tooling.GradleConnectionException
import org.gradle.tooling.ResultHandler
import kotlin.coroutines.resume


suspend fun BuildLauncher.runAsync() = suspendCancellableCoroutine<Result<Unit>> { cont ->
    run(object : ResultHandler<Void> {
        override fun onComplete(result: Void?) {
            if (cont.isActive) cont.resume(Result.success(Unit))
        }

        override fun onFailure(failure: GradleConnectionException) {
            if (cont.isActive) cont.resume(Result.failure(failure))
        }
    })

}
