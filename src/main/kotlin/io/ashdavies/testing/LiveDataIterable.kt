package io.ashdavies.testing

internal class LiveDataIterable<T> : LiveDataRegistry<T> {

  private val history: MutableList<T> = mutableListOf()

  override fun iterator(): Iterator<T> {
    return history.iterator()
  }

  override fun emit(vararg values: T) {
    history.addAll(values)
  }

  override fun expect(vararg values: T) {
    check(listOf(values) == history) { "Expecting values $values but it is actually $history" }
  }
}
