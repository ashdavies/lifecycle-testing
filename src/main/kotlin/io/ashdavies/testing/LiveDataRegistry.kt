package io.ashdavies.testing

interface LiveDataRegistry<T> : Iterable<T> {

  fun emit(vararg values: T)

  fun expect(vararg values: T)
}
