package com.example.webfluxdemo.repository.model

import org.springframework.data.annotation.Id

data class User(
    @Id
    val id: Long,
    val name: String
)
