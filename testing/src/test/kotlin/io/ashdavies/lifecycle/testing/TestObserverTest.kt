package io.ashdavies.lifecycle.testing

import org.junit.jupiter.api.Test

internal class TestObserverTest {

  private val observer: TestObserver<String> = TestObserver()

  @Test
  fun `should emit on changed`() {
    observer.onChanged("Hello")
    observer.onChanged("World")

    observer.expect("Hello", "World")
  }
}
