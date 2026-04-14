package com.example.data.remote.model


data class UserRegistrationRequest(
    val username: String,
    val password: String,
    val passCorrect: String,
    val email: String
)

data class UserRegistrationResponse(
    val message: String
)

data class ErrorResponse(
    val username: String?,
    val password: String?,
    val passCorrect: String?,
    val email: String?
)