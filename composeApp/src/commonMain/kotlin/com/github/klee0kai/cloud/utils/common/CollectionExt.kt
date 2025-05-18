package com.github.klee0kai.cloud.utils.common

inline fun <reified T> buildListCount(count: Int, action: () -> T): List<T> {
    val list = mutableListOf<T>()
    repeat(count) {
        list.add(action())
    }
    return list
}

inline fun <T> Iterable<T>.runForEach(action: T.() -> Unit) =
    forEach { action.invoke(it) }

inline fun <reified T> Iterable<T>.accumulate(action: (T, T) -> T): T? {
    var last: T? = null
    forEachIndexed { index, t ->
        last = if (index > 0) {
            action(last as T, t)
        } else {
            t
        }
    }
    return last
}

fun enumerateAllVariants(vararg dimensionVariants: List<Any?>) = sequence<List<Any?>> {
    val dimIdxs = buildListCount(dimensionVariants.size) { 0 }.toMutableList()
    while (true) {
        yield(
            buildList(dimensionVariants.size) {
                repeat(dimensionVariants.size) { idx ->
                    add(dimensionVariants[idx][dimIdxs[idx]])
                }
            }
        )

        for (i in dimensionVariants.indices) {
            if (dimIdxs[i] < dimensionVariants[i].size - 1) {
                dimIdxs[i]++
                break
            } else {
                dimIdxs[i] = 0
            }
        }
        if (dimIdxs.all { it == 0 }) break
    }
}