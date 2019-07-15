package io.ashdavies.lifecycle.testing

import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class MutableLiveDataTest {

  @Test
  fun `should emit values`() {
    val source: MutableLiveData<String> = MutableLiveData()
    val values: MutableList<String> = mutableListOf()

    source.observeForever {
      values += it
    }

    source.emit("Hello", "World")

    assertThat(values).isEqualTo(listOf("Hello", "World"))
  }
}
