package io.ashdavies.lifecycle.jupiter

import androidx.arch.core.executor.ArchTaskExecutor
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.concurrent.atomic.AtomicBoolean

@ExtendWith(InstantTaskExecutorExtension::class)
internal class InstantTaskExecutorExtensionTest {

  private val executor = ArchTaskExecutor.getInstance()

  @Test
  fun `should execute on disk io`() {
    val executed = AtomicBoolean(false)

    executor.executeOnDiskIO {
      executed.set(true)
    }

    assertThat(executed.get()).isTrue()
  }

  @Test
  fun `should post to main thread`() {
    val executed = AtomicBoolean(false)

    executor.executeOnMainThread {
      executed.set(true)
    }

    assertThat(executed.get()).isTrue()
  }

  @Test
  fun `should be on main thread`() {
    assertThat(executor.isMainThread).isTrue()
  }
}
