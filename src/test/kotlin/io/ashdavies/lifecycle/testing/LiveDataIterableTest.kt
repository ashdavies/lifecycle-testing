package io.ashdavies.lifecycle.testing

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.assertThrows
import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeoutException

internal class LiveDataIterableTest {

  private val iterable = LiveDataIterable<String>()

  @Test
  fun `should emit values`() {
    iterable.emit("Hello", "World")

    iterable.expect("Hello", "World")
  }

  @Test
  fun `should expect value`() {
    iterable.emit("Hello", "World")

    iterable.expect("Hello")
  }

  @Test
  fun `should expect values`() {
    iterable.emit("Hello", "World")

    iterable.expect(listOf("Hello", "World"))
  }

  @Test
  fun `should not interrupt unawaited thread`() {
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
}
