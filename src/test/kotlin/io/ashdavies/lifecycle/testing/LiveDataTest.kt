package io.ashdavies.lifecycle.testing

import androidx.lifecycle.Lifecycle.State.INITIALIZED
import androidx.lifecycle.Lifecycle.State.RESUMED
import androidx.lifecycle.MutableLiveData
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class LiveDataTest {

  private val source: MutableLiveData<String> = MutableLiveData()

  @Test
  fun `should not emit value in non started state`() {
    val observer: TestObserver<String> = source.test(INITIALIZED)

    source.emit("Hello World!")

    observer.expect()
  }

  @Test
  fun `should emit value in resumed state`() {
    val observer: TestObserver<String> = source.test(RESUMED)

    source.emit("Hello World")

    observer.expect("Hello World")
  }
}
