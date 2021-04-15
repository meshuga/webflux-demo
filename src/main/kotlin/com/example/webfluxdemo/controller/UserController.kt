package com.example.webfluxdemo.controller

import com.example.webfluxdemo.repository.UserRepository
import com.example.webfluxdemo.repository.model.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class UserController(
    val userRepository: UserRepository
) {
    @GetMapping("/")
    fun findAll(): Flux<User> {
        return userRepository.findAll()
    }
}