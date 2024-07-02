package com.example.alkewalletevalacion.domain.usecases
import android.content.Context
import com.example.alkewalletevalacion.data.network.api.AuthService
import com.example.alkewalletevalacion.data.network.response.AccessTokenResponse
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.data.network.response.LoginRequest
import com.example.alkewalletevalacion.data.network.response.NewAccountRequest
import com.example.alkewalletevalacion.data.network.response.UserResponse
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

    fun getUserInfo(token: String, callback: (Boolean, UserResponse?) -> Unit) {
        authService.getUserInfo().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    callback(true, response.body())
                } else {
                    callback(false, null)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                callback(false, null)
            }
        })
    }

    fun getAccountInfo(token: String, callback: (Boolean, List<AccountResponse>?) -> Unit) {
        authService.getAccountInfo().enqueue(object : Callback<List<AccountResponse>> {
            override fun onResponse(call: Call<List<AccountResponse>>, response: Response<List<AccountResponse>>) {
                if (response.isSuccessful) {
                    callback(true, response.body())
                } else {
                    callback(false, null)
                }
            }

            override fun onFailure(call: Call<List<AccountResponse>>, t: Throwable) {
                callback(false, null)
            }
        })
    }

    fun createAccountWithToken(newAccountRequest: NewAccountRequest, token: String, callback: (Boolean) -> Unit) {
        authService.createAccountWithToken(newAccountRequest, "Bearer $token").enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                callback(response.isSuccessful)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback(false)
            }
        })
    }
}