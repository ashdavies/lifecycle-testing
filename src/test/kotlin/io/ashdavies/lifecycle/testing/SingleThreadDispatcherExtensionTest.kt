package io.ashdavies.lifecycle.testing

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.launch
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.concurrent.atomic.AtomicBoolean

@ExtendWith(SingleThreadDispatcherExtension::class)
internal class SingleThreadDispatcherExtensionTest {

  @Test
  @Disabled
  fun `should execute on main dispatcher`() {
    val executed = AtomicBoolean(false)

    MainCoroutineScope.launch {
      executed.set(true)
    }

    assertThat(executed.get()).isTrue()
  }
}
