package com.example.alkewalletevalacion.domain.usecases

import com.example.alkewalletevalacion.data.network.api.AuthService
import com.example.alkewalletevalacion.data.network.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class UserInfoUseCase(private val authService: AuthService) {
    fun getUserInfo(onResult: (Boolean, UserResponse?) -> Unit) {
        authService.getUserInfo().enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    onResult(true, response.body())
                } else {
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                onResult(false, null)
            }
        })
    }
}