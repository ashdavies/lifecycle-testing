package io.ashdavies.lifecycle.testing

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.assertThrows
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
  @Timeout(value = 100, unit = MILLISECONDS)
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
  fun `should provide expected value exception`() {
    val exception: AssertionError = assertThrows {
      iterable.expect("Hello")
    }

    assertThat(exception.message).isEqualTo(
        """
missing (1): Hello
---
expected   : [Hello]
but was    : []
    """.trimIndent()
    )
  }

  @Test
  fun `should expect last value`() {
    iterable.emit("Hello", "World")

    iterable.last("World")
  }

  @Test
  fun `should provide last value exception`() {
    iterable.emit("Hello", "World")

    val exception: AssertionError = assertThrows {
      iterable.last("Hello")
    }

    assertThat(exception.message).isEqualTo(
        """
value of: iterable.onlyElement()
expected: Hello
but was : World
        """.trimIndent()
    )
  }

  @Test
  fun `should never expect value`() {
    iterable.emit("Hello")

    iterable.never("World")
  }

  @Test
  fun `should provide never value exception`() {
    iterable.emit("Hello", "World")

    val exception: AssertionError = assertThrows {
      iterable.never("Hello")
    }

    assertThat(exception.message).isEqualTo(
        """
expected not to contain any of: [Hello]
but contained                 : [Hello]
full contents                 : [Hello, World]
        """.trimIndent()
    )
  }

  @Test
  fun `should expect value once`() {
    iterable.emit("Hello", "World")

    iterable.once("Hello")
  }

  @Test
  fun `should provide once value exception`() {
    iterable.emit("Hello")

    val exception: AssertionError = assertThrows {
      iterable.once("World")
    }

    assertThat(exception.message).isEqualTo(
        """
expected to contain: World
but was            : [Hello]
        """.trimIndent()
    )
  }
}
