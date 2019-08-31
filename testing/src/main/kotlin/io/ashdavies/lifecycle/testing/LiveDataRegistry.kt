package io.ashdavies.lifecycle.testing

import java.util.concurrent.TimeUnit

interface LiveDataRegistry<T> : Iterable<T> {

  fun await()

  fun await(timeout: Long, unit: TimeUnit)

  fun expect(vararg values: T)

  fun last(vararg values: T)

  fun never(vararg values: T)

  fun once(value: T)
}
