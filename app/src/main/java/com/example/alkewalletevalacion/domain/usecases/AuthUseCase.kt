package com.example.alkewalletevalacion.domain.usecases
import android.content.Context
import com.example.alkewalletevalacion.data.network.api.AuthService
import com.example.alkewalletevalacion.data.network.response.AccessTokenResponse
import com.example.alkewalletevalacion.data.network.response.LoginRequest
import com.example.alkewalletevalacion.data.network.retrofit.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthUseCase(private val authService: AuthService) {
    fun authenticateUser(request: LoginRequest, callback: (Boolean, String?) -> Unit) {
        authService.login(request).enqueue(object : Callback<AccessTokenResponse> {
            override fun onResponse(call: Call<AccessTokenResponse>, response: Response<AccessTokenResponse>) {
                if (response.isSuccessful) {
                    val accessToken = response.body()?.accessToken
                    if (!accessToken.isNullOrBlank()) {
                        callback(true, accessToken)
                    } else {
                        callback(false, "Token no válido")
                    }
                } else {
                    callback(false, "Error en la respuesta del servidor: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<AccessTokenResponse>, t: Throwable) {
                callback(false, "Error de red: ${t.localizedMessage}")
            }
        })
    }
}