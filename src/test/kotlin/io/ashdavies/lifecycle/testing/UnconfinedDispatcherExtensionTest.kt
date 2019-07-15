package io.ashdavies.lifecycle.testing

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.launch
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.concurrent.atomic.AtomicBoolean

@ExtendWith(UnconfinedDispatcherExtension::class)
internal class UnconfinedDispatcherExtensionTest {

  @Test
  fun `should execute on main dispatcher`() {
    val executed = AtomicBoolean(false)

    MainCoroutineScope.launch {
      executed.set(true)
    }

    assertThat(executed.get()).isTrue()
  }
}
