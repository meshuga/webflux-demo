package com.example.webfluxdemo.client

import com.example.webfluxdemo.client.model.Hobby
import org.springframework.stereotype.Service

@Service
class HobbyClient {
    suspend fun getUserHobby(userId: Long): Hobby {
        return Hobby(1, "poker")
    }
}