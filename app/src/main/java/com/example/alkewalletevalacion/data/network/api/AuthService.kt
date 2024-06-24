package com.example.alkewalletevalacion.data.network.api

import com.example.alkewalletevalacion.data.network.response.AccessTokenResponse
import com.example.alkewalletevalacion.data.network.response.LoginRequest
import com.example.alkewalletevalacion.data.network.response.UserResponse
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.data.network.response.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    /**
     * Posteo para login
     */
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<AccessTokenResponse>

    /**
     * Informacion de usuario logueado
     */
    @GET("auth/me")
    fun getUserInfo(): Call<UserResponse>

    /**.
     * Informacion sobre mi cuenta
     */
    @GET("accounts/me")
    fun getAccountInfo(): Call<List<AccountResponse>>

    /**
     * Traer listado de usuarios
     *      */
    @GET("users")
    fun getUsers(): Call<List<User>>
}