package com.example.myapp.auth

import retrofit2.http.GET

interface UserService {

    data class ApiResponse(
        val id: Int,
        val name: String,
        val email: String
    )

    @GET("mikkel1510/my-android-project/refs/heads/fetching-data/users.json")
    suspend fun getUsers(): List<ApiResponse>
}