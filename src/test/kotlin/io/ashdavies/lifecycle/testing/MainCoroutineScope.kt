package io.ashdavies.lifecycle.testing

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

internal object MainCoroutineScope : CoroutineScope {

  override val coroutineContext: CoroutineContext = Dispatchers.Main
}
