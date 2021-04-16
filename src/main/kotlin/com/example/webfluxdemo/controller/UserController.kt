package com.example.webfluxdemo.controller

import com.example.webfluxdemo.client.HobbyClient
import com.example.webfluxdemo.controller.model.UserResponse
import com.example.webfluxdemo.repository.UserRepository
import kotlinx.coroutines.reactor.mono
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toFlux

@RestController
@RequestMapping("/user")
class UserController(
    val userRepository: UserRepository,
    val hobbyClient: HobbyClient
) {
    @GetMapping
    fun findUsersInfo(): Flux<UserResponse> {
        val users = userRepository.findAll()
        val hobbiesMono = mono { hobbyClient.getUsersHobby() }
        return hobbiesMono.toFlux().flatMap { hobbies ->
            users.map { user -> UserResponse.new(user, hobbies[0]) }
        }
    }
}
