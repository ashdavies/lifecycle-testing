package io.ashdavies.lifecycle.jupiter

import com.google.common.truth.FailureMetadata
import com.google.common.truth.IterableSubject
import io.ashdavies.lifecycle.testing.TestObserver

internal class TestObserverSubject<T>(
    metadata: FailureMetadata,
    actual: TestObserver<T>
) : IterableSubject(metadata, actual)
