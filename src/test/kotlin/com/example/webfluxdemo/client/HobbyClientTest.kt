package com.example.webfluxdemo.client

import com.example.webfluxdemo.client.model.Hobby
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks

@ExtendWith(MockKExtension::class)
class HobbyClientTest {

    @InjectMocks
    val sut = HobbyClient()

    @Test
    fun onCallShouldReturnHobby() {
        val result = runBlocking {
            sut.getUserHobby(1L)
        }

        assertThat(result).isEqualTo(Hobby(1, "poker"))
    }
}