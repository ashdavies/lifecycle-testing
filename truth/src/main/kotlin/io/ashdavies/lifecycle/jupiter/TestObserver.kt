package io.ashdavies.lifecycle.jupiter

import com.google.common.truth.IterableSubject
import com.google.common.truth.Subject.Factory
import com.google.common.truth.Truth.assertAbout
import io.ashdavies.lifecycle.testing.TestObserver

private fun <T> factory(): Factory<TestObserverSubject<T>, TestObserver<T>> = Factory(::TestObserverSubject)

fun <T> TestObserver<T>.assertAbout(): IterableSubject = assertAbout(factory<T>()).that(this)
