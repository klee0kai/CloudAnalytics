package com.github.klee0kai.cloud.utils.error

import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.reflect.KClass

fun Throwable.causes() = generateSequence(this) { it.cause }

inline fun <reified T : Any> Throwable.cause(cl: KClass<T>) =
    causes().firstNotNullOfOrNull { cl as? T }

inline fun <reified T> Throwable.cause() =
    causes().firstNotNullOfOrNull { it as? T }

inline fun <reified T : Any> Throwable.isCause(cl: KClass<T>) = cause(cl) != null

object CoroutineHandlers {
    val debugHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Logger.d(throwable.message ?: "", throwable)
    }

    val warningHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Logger.w(throwable.message ?: "", throwable)
    }

    val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Logger.w(throwable.message ?: "", throwable)
    }

    val wtfHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Logger.a(throwable.message ?: "", throwable)
    }
}