package io.ashdavies.lifecycle.testing

import androidx.lifecycle.LiveData
import com.google.common.truth.FailureMetadata
import com.google.common.truth.IterableSubject
import com.google.common.truth.Subject.Factory
import com.google.common.truth.Truth.assertAbout

class TestObserverSubject<T>(
    metadata: FailureMetadata,
    actual: TestObserver<T>
) : IterableSubject(metadata, actual) {

  companion object {

    internal fun <T> factory(): Factory<TestObserverSubject<T>, TestObserver<T>> = Factory(::TestObserverSubject)

    fun <T> assertThat(actual: LiveData<T>?): TestObserverSubject<T> = assertAbout(factory<T>()).that(actual?.test())
  }
}
