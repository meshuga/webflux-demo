package com.example.webfluxdemo.controller

import com.example.webfluxdemo.client.HobbyClient
import com.example.webfluxdemo.client.model.Hobby
import com.example.webfluxdemo.controller.model.UserResponse
import com.example.webfluxdemo.repository.UserRepository
import com.example.webfluxdemo.repository.model.User
import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux

@WebFluxTest
@ContextConfiguration
class UserControllerTest(
    @Autowired
    val client: WebTestClient
) {
    @MockkBean
    lateinit var userRepository: UserRepository

    @MockkBean
    lateinit var hobbyClient: HobbyClient

    @Test
    fun onGettingUserByIdShouldReturnEntity() {
        every { userRepository.findAll() } returns Flux.just(User(1L, "John Doe"))
        coEvery { hobbyClient.getUsersHobby() } returns listOf(Hobby(1L, "John Doe"))

        val result = client
            .get().uri("/user")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(UserResponse::class.java)
            .returnResult()

        assertThat(result.responseBody).hasSize(1)

        verify(exactly = 1) { userRepository.findAll() }
        coVerify(exactly = 1) { hobbyClient.getUsersHobby() }
    }
}
