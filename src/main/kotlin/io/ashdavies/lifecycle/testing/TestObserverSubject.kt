package io.ashdavies.lifecycle.testing

import com.google.common.truth.FailureMetadata
import com.google.common.truth.IterableSubject
import com.google.common.truth.Subject.Factory

class TestObserverSubject<T>(
    metadata: FailureMetadata,
    actual: TestObserver<T>
) : IterableSubject(metadata, actual) {

  companion object {

    internal fun <T> factory(): Factory<TestObserverSubject<T>, TestObserver<T>> = Factory(::TestObserverSubject)
  }
}
