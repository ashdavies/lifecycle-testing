package io.ashdavies.testing

import java.util.concurrent.TimeUnit

interface LiveDataRegistry<T> : Iterable<T> {

  fun emit(vararg values: T)

  fun expect(vararg values: T)

  fun await()

  fun await(timeout: Long, unit: TimeUnit)

  fun reset()
}
