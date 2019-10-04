package io.ashdavies.lifecycle.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class CoroutineScopeTest {

  @Test
  fun `should catch nested scope failure`() = runBlocking<Unit> {
    assertThrows<IllegalStateException> {
      exception()
    }
  }

  private suspend fun exception(): String = coroutineScope<String> {
    throw IllegalStateException("Hello World")
  }
}
