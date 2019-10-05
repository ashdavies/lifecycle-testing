package io.ashdavies.lifecycle.jupiter

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class AssertionsTest {

  @Test
  fun `should catch nested scope failure`() = runBlocking<Unit> {
    assertThrows<IllegalStateException> {
      throw IllegalStateException()
    }
  }

  @Test
  fun `should catch nested result failure`() = runBlocking<Unit> {
    assertThrows<IllegalStateException>(runCatching {
      throw IllegalStateException()
    })
  }

  @Test
  fun `should catch nested failure with message`() = runBlocking<Unit> {
    val exception: IllegalStateException = assertThrows("Quack") {
      throw IllegalStateException("Quack")
    }

    assertThat(exception.message).isEqualTo("Quack")
  }

  @Test
  fun `should catch nested failure with message supplier`() = runBlocking<Unit> {
    val exception: IllegalStateException = assertThrows({ "Quack" }) {
      throw IllegalStateException("Quack")
    }

    assertThat(exception.message).isEqualTo("Quack")
  }

  @Test
  fun `should catch nested result failure with message supplier`() = runBlocking<Unit> {
    val exception: IllegalStateException = assertThrows({ "Quack" }, runCatching {
      throw IllegalStateException("Quack")
    })

    assertThat(exception.message).isEqualTo("Quack")
  }
}
