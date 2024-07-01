package com.example.alkewalletevalacion.domain.usecases


import android.util.Log
import com.example.alkewalletevalacion.data.network.api.AuthService
import com.example.alkewalletevalacion.data.network.response.AccessTokenResponse
import com.example.alkewalletevalacion.data.network.response.NewAccountRequest
import com.example.alkewalletevalacion.data.network.response.NewUserRequest
import com.example.alkewalletevalacion.data.network.response.NewUserResponse
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.data.network.response.LoginRequest
import retrofit2.Call

class RegisterUserUseCase(private val authService: AuthService) {

    suspend fun execute(newUserRequest: NewUserRequest): NewUserResponse {
        return authService.registerUser(newUserRequest)
    }

    suspend fun createAccount(newAccountRequest: NewAccountRequest): AccountResponse {
        return authService.createAccount(newAccountRequest)
    }

    // Método para crear una cuenta con token de autorización
    suspend fun createAccountWithToken(newAccountRequest: NewAccountRequest, token: String): Boolean {
        return authService.createAccountWithToken("Bearer $token", newAccountRequest)
    }
}