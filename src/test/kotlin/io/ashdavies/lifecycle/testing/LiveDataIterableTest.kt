package io.ashdavies.lifecycle.testing

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import java.lang.Thread.State.WAITING
import java.util.concurrent.CountDownLatch

internal class LiveDataIterableTest {

  private val iterable = LiveDataIterable<String>()

  @Test
  fun `should await value indefinitely`() {
    val latch = CountDownLatch(1)

    val thread = Thread(Runnable {
      latch.countDown()
      iterable.await()
    })

    thread.start()
    latch.await()

    assertThat(thread.state).isEqualTo(WAITING)
  }

  @Test
  fun `should await value`() {
    Thread(Runnable {
      iterable.emit("Hello World")
    }).start()

    iterable.await()
  }

  @Test
  fun `should emit values`() {
    iterable.emit("Hello", "World")

    iterable.expect("Hello", "World")
  }

  @Test
  fun `should expect values`() {
    iterable.emit("Hello", "World")

    iterable.expect("Hello", "World")
  }

  @Test
  fun `should expect last value`() {
    iterable.emit("Hello", "World")

    iterable.last("World")
  }

  @Test
  fun `should never expect value`() {
    iterable.emit("Hello")

    iterable.never("World")
  }

  @Test
  fun `should expect value once`() {
    iterable.emit("Hello", "World")

    iterable.once("Hello")
  }
}
