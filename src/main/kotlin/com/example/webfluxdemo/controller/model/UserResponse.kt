package com.example.webfluxdemo.controller.model

import com.example.webfluxdemo.client.model.Hobby
import com.example.webfluxdemo.repository.model.User

data class UserResponse(
    val id: String,
    val name: String,
    val hobbyName: String
) {
    companion object {
        fun new(user: User, hobby: Hobby) = UserResponse(user.id, user.name, hobby.name)
    }
}