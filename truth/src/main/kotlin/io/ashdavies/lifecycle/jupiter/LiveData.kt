package io.ashdavies.lifecycle.jupiter

import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LiveData
import com.google.common.truth.IterableSubject
import io.ashdavies.lifecycle.testing.test

fun <T> LiveData<T>.assertAbout(state: State = STARTED): IterableSubject = test(state).assertAbout()
