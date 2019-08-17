package io.ashdavies.lifecycle.testing

import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertAbout
import io.ashdavies.lifecycle.testing.TestObserverSubject.Companion.factory

class TestObserver<T> private constructor(private val register: LiveDataRegister<T>) : LiveDataRegistry<T> by register, Observer<T> {

  internal constructor() : this(LiveDataIterable())

  override fun onChanged(it: T) = register.emit(it)

  fun assertAbout(): TestObserverSubject<T> = assertAbout(factory<T>()).that(this)
}
