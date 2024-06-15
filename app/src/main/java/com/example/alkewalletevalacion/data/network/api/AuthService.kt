package com.example.alkewalletevalacion.data.network.api

import com.example.alkewalletevalacion.data.network.response.AccessTokenResponse
import com.example.alkewalletevalacion.data.network.response.LoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<AccessTokenResponse>
}