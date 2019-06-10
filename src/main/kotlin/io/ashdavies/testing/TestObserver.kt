package io.ashdavies.testing

import androidx.lifecycle.Observer

class TestObserver<T> : LiveDataRegistry<T> by LiveDataIterable(), Observer<T> {

  override fun onChanged(it: T) = emit(it)
}
