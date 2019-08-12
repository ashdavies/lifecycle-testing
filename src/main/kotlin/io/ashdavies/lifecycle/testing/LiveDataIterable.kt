package io.ashdavies.lifecycle.testing

import com.google.common.truth.Truth.assertThat
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

internal class LiveDataIterable<T> : LiveDataRegistry<T> {

  private val history: MutableList<T> = mutableListOf()
  private var latch = CountDownLatch(0)

  override fun await() {
    latch = CountDownLatch(1)
    latch.await()
  }

  override fun await(timeout: Long, unit: TimeUnit) {
    latch = CountDownLatch(1)
    latch.await(timeout, unit)
  }

  override fun iterator(): Iterator<T> {
    return history.iterator()
  }

  override fun emit(vararg values: T) {
    history.addAll(values)
    latch.countDown()
  }

  override fun expect(vararg values: T) {
    assertThat(history)
        .containsExactly(values)
        .inOrder()
  }

  override fun last(vararg values: T) {
    assertThat(history.takeLast(values.size))
        .containsExactly(values)
        .inOrder()
  }

  override fun never(vararg values: T) {
    assertThat(history).containsNoneIn(values)
  }

  override fun once(value: T) {
    assertThat(history).containsExactly(value)
  }
}
