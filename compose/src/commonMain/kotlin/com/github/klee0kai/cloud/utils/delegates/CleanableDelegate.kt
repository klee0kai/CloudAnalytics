package com.github.klee0kai.cloud.utils.delegates

import com.github.klee0kai.cloud.utils.common.Cleanable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class CleanableDelegate<T : Any?> constructor() : ReadWriteProperty<Any?, T?> {

    var value: T? = null

    constructor(value: T?) : this() {
        this.value = value
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? = value

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        this.value = value
    }

    override fun toString(): String = "Weak( ${value} )"

    fun clean() {
        (value as? Cleanable)?.clean()
        value = null
    }

}