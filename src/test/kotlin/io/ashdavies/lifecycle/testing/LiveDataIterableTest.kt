package io.ashdavies.lifecycle.testing

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.assertThrows
import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeoutException

internal class LiveDataIterableTest {

  private val iterable = LiveDataIterable<String>()

  @Test
  fun `should not interrupt awaited thread`() {
    Thread
        .currentThread()
        .interrupt()
  }

  @Test
  @Timeout(value = 100, unit = MILLISECONDS)
  fun `should await value indefinitely`() {
    assertThrows<TimeoutException> {
      iterable.await()
    }
  }

  @Test
  @Timeout(value = 100, unit = MILLISECONDS)
  fun `should await value`() {
    iterable.await()

    iterable.emit("Hello")
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
