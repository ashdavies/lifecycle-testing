package io.ashdavies.lifecycle.testing

import androidx.lifecycle.MutableLiveData

internal fun <T> MutableLiveData<T>.emit(vararg values: T) = values.forEach(::setValue)
