package com.github.klee0kai.cloud.core.utils.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob

fun CoroutineScope.childSupervisedScope(
) = CoroutineScope(coroutineContext + SupervisorJob(coroutineContext[Job]))

