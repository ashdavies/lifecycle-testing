package io.ashdavies.lifecycle.testing

import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.test(state: State = STARTED): TestObserver<T> = test(TestLifecycleOwner(state))

fun <T> LiveData<T>.test(owner: LifecycleOwner): TestObserver<T> {
  val observer = TestObserver<T>()
  observe(owner, observer)
  return observer
}
