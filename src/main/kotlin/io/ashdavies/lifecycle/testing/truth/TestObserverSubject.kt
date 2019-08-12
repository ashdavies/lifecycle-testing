package io.ashdavies.lifecycle.testing.truth

import com.google.common.truth.FailureMetadata
import com.google.common.truth.IterableSubject
import com.google.common.truth.Subject.Factory
import com.google.common.truth.Truth.assertAbout
import io.ashdavies.lifecycle.testing.TestObserver

internal class TestObserverSubject<T>(
    metadata: FailureMetadata,
    actual: TestObserver<T>
) : IterableSubject(metadata, actual) {

  companion object {

    private fun <T> factory(): Factory<TestObserverSubject<T>, TestObserver<T>> = Factory(::TestObserverSubject)

    fun <T> assertThat(actual: TestObserver<T>?): TestObserverSubject<T> = assertAbout(
        factory<T>()
    ).that(actual)
  }
}
