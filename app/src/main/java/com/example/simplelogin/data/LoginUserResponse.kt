package com.example.simplelogin.data

data class LoginUserResponse(
    val login: Boolean,
    val message: String,
    val token: String
)