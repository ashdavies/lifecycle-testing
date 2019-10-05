package io.ashdavies.lifecycle.jupiter

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.function.Executable
import java.util.function.Supplier

inline fun <reified T : Throwable> assertThrows(executable: () -> Unit): T {
  return assertThrows(runCatching(executable))
}

inline fun <reified T : Throwable> assertThrows(result: Result<*>): T {
  return assertThrows(T::class.java) { result.getOrThrow() }
}

inline fun <reified T : Throwable> assertThrows(message: String, executable: () -> Unit): T {
  return assertThrows({ message }, executable)
}

inline fun <reified T : Throwable> assertThrows(noinline message: () -> String, executable: () -> Unit): T {
  return assertThrows(message, runCatching(executable))
}

inline fun <reified T : Throwable> assertThrows(noinline message: () -> String, result: Result<*>): T {
  return assertThrows(T::class.java, Executable { result.getOrThrow() }, Supplier(message))
}
