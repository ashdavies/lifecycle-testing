package io.ashdavies.lifecycle.jupiter

import androidx.lifecycle.MutableLiveData
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class LiveDataTest {

  @Test
  fun `should assert about live data value`() {
    val source = MutableLiveData<String>("Hello World")

    assertThat(source).containsExactly("Hello World")
  }
}
