package io.ashdavies.lifecycle.testing

import androidx.lifecycle.Lifecycle.State.INITIALIZED
import androidx.lifecycle.Lifecycle.State.RESUMED
import androidx.lifecycle.MutableLiveData
import org.junit.jupiter.api.Test

internal class LiveData {

  private val source: MutableLiveData<String> = MutableLiveData()

  @Test
  fun `should not emit value in non started state`() {
    val observer: TestObserver<String> = source.test(INITIALIZED)

    source.emit("Hello World!")

    observer.expect()
  }

  @Test
  internal fun `should emit value in resumed state`() {
    val observer: TestObserver<String> = source.test(RESUMED)

    source.emit("Hello World")

    observer.expect("Hello World")
  }
}
