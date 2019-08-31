package io.ashdavies.lifecycle.testing

import androidx.lifecycle.Observer

class TestObserver<T> private constructor(
    private val register: LiveDataRegister<T>
) : LiveDataRegistry<T> by register, Observer<T> {

  internal constructor() : this(LiveDataIterable())

  override fun onChanged(it: T) = register.emit(it)
}
