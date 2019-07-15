package io.ashdavies.lifecycle.testing

import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.Lifecycle.State.CREATED
import androidx.lifecycle.Lifecycle.State.DESTROYED
import androidx.lifecycle.Lifecycle.State.INITIALIZED
import androidx.lifecycle.Lifecycle.State.RESUMED
import androidx.lifecycle.Lifecycle.State.STARTED
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class TestLifecycleOwnerTest {

  private val owner = TestLifecycleOwner(INITIALIZED)

  private val state: State
    get() = owner
        .lifecycle
        .currentState

  @Test
  fun `should start in provided started initialised state`() {
    assertThat(state).isEqualTo(INITIALIZED)
  }

  @Test
  fun `should handle on create`() {
    owner.onCreate()

    assertThat(state).isEqualTo(CREATED)
  }

  @Test
  fun `should handle on start`() {
    owner.onStart()

    assertThat(state).isEqualTo(STARTED)
  }

  @Test
  fun `should handle on resume`() {
    owner.onResume()

    assertThat(state).isEqualTo(RESUMED)
  }

  @Test
  fun `should handle on pause`() {
    owner.onPause()

    assertThat(state).isEqualTo(STARTED)
  }

  @Test
  fun `should handle on stop`() {
    owner.onStop()

    assertThat(state).isEqualTo(CREATED)
  }

  @Test
  fun `should handle on destroy`() {
    owner.onDestroy()

    assertThat(state).isEqualTo(DESTROYED)
  }
}
