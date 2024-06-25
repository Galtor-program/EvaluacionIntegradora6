package com.example.alkewalletevalacion.data.network.api

import com.example.alkewalletevalacion.data.network.response.AccessTokenResponse
import com.example.alkewalletevalacion.data.network.response.LoginRequest
import com.example.alkewalletevalacion.data.network.response.UserResponse
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.data.network.response.TransactionRequest
import com.example.alkewalletevalacion.data.network.response.TransactionResponse
import com.example.alkewalletevalacion.data.network.response.UserListWrapper
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
    @GET("users?limit=5")
    fun getUsers(): Call<UserListWrapper>

    /**
     * Transacciones realizadas por el usuario Logueado
     */
    @GET("transactions")
    fun getTransactions(): Call<List<TransactionResponse>>

    /**
     * Realizar una Transferencia
     */
    @POST("transactions")
    fun createTransaction(@Body request: TransactionRequest): Call<TransactionResponse>
}