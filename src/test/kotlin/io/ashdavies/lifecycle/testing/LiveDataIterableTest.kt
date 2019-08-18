package io.ashdavies.lifecycle.testing

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.lang.Thread.State.WAITING
import java.util.concurrent.TimeUnit.MILLISECONDS

internal class LiveDataIterableTest {

  private val iterable = LiveDataIterable<String>()

  @Test
  @Timeout(value = 100, unit = MILLISECONDS)
  fun `should await value indefinitely`() {
    val thread = Thread(Runnable {
      iterable.await()
    })

    thread.start()

    while (thread.state != WAITING) {
    }
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
