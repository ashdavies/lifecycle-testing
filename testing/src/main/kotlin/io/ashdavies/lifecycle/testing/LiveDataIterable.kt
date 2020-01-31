package io.ashdavies.lifecycle.testing

import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

internal class LiveDataIterable<T> : LiveDataRegister<T> {

  private val actions = AtomicInteger()
  private val history: MutableList<T> = mutableListOf()
  private var latch = CountDownLatch(0)

  override fun emit(vararg values: T) {
    history.addAll(values)
    latch.countDown()
  }

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

  override fun check(vararg values: T, message: () -> String) {
    assertWithMessage(message())
        .that(history)
        .containsExactly(*values)
        .inOrder()
  }

  override fun check(vararg values: T) {
    assertThat(history)
        .containsExactly(*values)
        .inOrder()
  }

  override fun expect(index: Int) {
    assertThat(actions.incrementAndGet()).isEqualTo(index)
  }

  override fun last(vararg values: T) {
    assertThat(history.takeLast(values.size))
        .containsExactly(*values)
        .inOrder()
  }

  override fun never(vararg values: T) {
    assertThat(history).containsNoneIn(values)
  }

  override fun once(value: T) {
    assertThat(history).contains(value)
  }
}
