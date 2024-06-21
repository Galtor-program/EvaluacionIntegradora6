package com.example.alkewalletevalacion.domain.usecases

import android.util.Log
import com.example.alkewalletevalacion.data.network.api.AuthService
import com.example.alkewalletevalacion.data.network.response.TransferRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransferUseCase(private val authService: AuthService) {
    fun transfer(request: TransferRequest, callback: (Boolean) -> Unit) {
        authService.transfer(request).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    callback(true)
                } else {
                    callback(false)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("TransferUseCase", "Transfer onFailure: ${t.message}", t)
                callback(false)
            }
        })
    }
}