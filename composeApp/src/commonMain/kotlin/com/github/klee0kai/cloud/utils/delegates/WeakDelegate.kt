package com.github.klee0kai.cloud.utils.delegates

import java.lang.ref.WeakReference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class WeakDelegate<T : Any?> constructor() : ReadWriteProperty<Any?, T?> {

    private var ref: WeakReference<T>? = null

    constructor(value: T?) : this() {
        ref = WeakReference(value)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? = ref?.get()

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        ref = WeakReference(value)
    }

    override fun toString(): String = "Weak( ${ref?.get()} )"

}