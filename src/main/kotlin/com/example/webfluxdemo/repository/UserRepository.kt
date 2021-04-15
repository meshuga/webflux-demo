package com.example.webfluxdemo.repository

import com.example.webfluxdemo.repository.model.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface UserRepository: ReactiveCrudRepository<User, Long> {
}