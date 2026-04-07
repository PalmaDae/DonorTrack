package com.example.data.remote.model


// UserRegistrationRequest.kt
data class UserRegistrationRequest(
    val login: String,
    val password: String,
    val passCorrect: String,
    val email: String
)

// UserRegistrationResponse.kt
data class UserRegistrationResponse(
    val message: String?,
    val userId: Long?,
    val login: String?,
    val email: String?,
    val error: String?
)

// ErrorResponse.kt (для ошибок валидации)
data class ErrorResponse(
    val login: String?,
    val password: String?,
    val passCorrect: String?,
    val email: String?
)