package com.example.alkewalletevalacion.data.network.api

import com.example.alkewalletevalacion.data.network.response.AccessTokenResponse
import com.example.alkewalletevalacion.data.network.response.LoginRequest
import com.example.alkewalletevalacion.data.network.response.UserResponse
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.data.network.response.TransferRequest
import com.example.alkewalletevalacion.data.network.response.UserListResponse
import com.example.alkewalletevalacion.data.network.response.UserListWrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    /**
     * Realizar el login
     */
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<AccessTokenResponse>

    /**
     * Informacion del usuario logueado
     */
    @GET("auth/me")
    fun getUserInfo(): Call<UserResponse>

    /**
     * Informacion de la cuenta del usuario logueado
     */
    @GET("accounts/me")
    fun getAccountInfo(): Call<List<AccountResponse>>

    /**
     * Los 5 primeros usuarios creados en la api
     */
    @GET("users?limit=5")
    fun getUsers(): Call<UserListWrapper>


    @POST("transactions")
    fun transfer(@Body request: TransferRequest): Call<Void>
}