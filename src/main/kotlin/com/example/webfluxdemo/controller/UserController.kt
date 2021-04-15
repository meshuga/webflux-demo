package com.example.webfluxdemo.controller

import com.example.webfluxdemo.client.HobbyClient
import com.example.webfluxdemo.controller.model.UserResponse
import com.example.webfluxdemo.repository.UserRepository
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController("/user")
class UserController(
    val userRepository: UserRepository,
    val hobbyClient: HobbyClient
) {
    @GetMapping("/{userId}")
    suspend fun findUserInfo(@PathVariable userId: Long): UserResponse {
        val user = userRepository.findById(userId).awaitSingle()
        val hobby = hobbyClient.getUserHobby(userId)
        return UserResponse.new(user, hobby)
    }
}