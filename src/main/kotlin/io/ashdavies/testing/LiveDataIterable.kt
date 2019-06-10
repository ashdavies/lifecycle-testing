package io.ashdavies.testing

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

internal class LiveDataIterable<T> : LiveDataRegistry<T> {

  private val history: MutableList<T> = mutableListOf()
  private var latch = CountDownLatch(1)

  override fun iterator(): Iterator<T> {
    return history.iterator()
  }

  override fun emit(vararg values: T) {
    history.addAll(values)
  }

  override fun expect(vararg values: T) {
    check(listOf(values) == history) { "Expecting values $values but it is actually $history" }
  }

  override fun await() {
    latch.await()
  }

  override fun await(timeout: Long, unit: TimeUnit) {
    latch.await(timeout, unit)
  }

  override fun reset() {
    latch = CountDownLatch(1)
  }
}
