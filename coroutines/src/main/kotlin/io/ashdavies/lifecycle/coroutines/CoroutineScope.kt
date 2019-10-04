package io.ashdavies.lifecycle.coroutines

import org.junit.jupiter.api.Assertions.assertThrows

inline fun <reified T : Throwable> assertThrows(block: () -> Unit): T = runCatching(block) assertThrows T::class.java

infix fun <T : Throwable> Result<*>.assertThrows(kls: Class<T>): T = assertThrows(kls) { getOrThrow() }
