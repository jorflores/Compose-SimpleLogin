package com.example.simplelogin.service

import com.example.simplelogin.data.LoginUserRequest
import com.example.simplelogin.data.LoginUserResponse
import com.example.simplelogin.data.RegisterUserRequest
import com.example.simplelogin.data.RegisterUserResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {


    companion object {
        val instance: UserService = Retrofit.Builder().baseUrl("https://login-api-dev-zetb.3.us-1.fl0.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }


    @POST("api/users/add")
    suspend fun registerUser(@Body user: RegisterUserRequest): RegisterUserResponse

    @POST("api/users/login")
    suspend fun loginUser(@Body user: LoginUserRequest ): LoginUserResponse


}