package io.ashdavies.lifecycle.testing

import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.common.truth.Truth.assertAbout
import io.ashdavies.lifecycle.testing.TestObserverSubject.Companion.factory
import java.util.concurrent.TimeUnit

fun <T> assertThat(actual: LiveData<T>?): TestObserverSubject<T> = assertAbout(factory<T>()).that(actual?.test())

fun <T> LiveData<T>.await() = test().await()

fun <T> LiveData<T>.await(timeout: Long, unit: TimeUnit) = test().await(timeout, unit)

fun <T> LiveData<T>.expect(vararg values: T) = test().expect(*values)

fun <T> LiveData<T>.last(vararg values: T) = test().expect(*values)

fun <T> LiveData<T>.never(vararg values: T) = test().never(*values)

fun <T> LiveData<T>.once(value: T) = test().once(value)

fun <T> LiveData<T>.test(state: State = STARTED): TestObserver<T> = test(TestLifecycleOwner(state))

fun <T> LiveData<T>.test(owner: LifecycleOwner): TestObserver<T> {
  val observer = TestObserver<T>()
  observe(owner, observer)
  return observer
}
