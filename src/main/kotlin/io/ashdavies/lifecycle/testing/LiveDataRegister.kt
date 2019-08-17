package io.ashdavies.lifecycle.testing

internal interface LiveDataRegister<T> : LiveDataRegistry<T> {

  fun emit(vararg values: T)
}
