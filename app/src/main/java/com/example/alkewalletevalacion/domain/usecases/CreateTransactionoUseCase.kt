package com.example.alkewalletevalacion.domain.usecases

import android.util.Log
import com.example.alkewalletevalacion.data.network.api.AuthService
import com.example.alkewalletevalacion.data.network.response.TransactionRequest
import com.example.alkewalletevalacion.data.network.response.TransactionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateTransactionUseCase(private val authService: AuthService) {
    fun createTransaction(transactionRequest: TransactionRequest, callback: (Boolean, TransactionResponse?) -> Unit) {
        authService.createTransaction(transactionRequest).enqueue(object : Callback<TransactionResponse> {
            override fun onResponse(call: Call<TransactionResponse>, response: Response<TransactionResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d("CreateTransactionUseCase", "createTransaction - Response Body: $responseBody")
                        callback(true, responseBody)
                    } else {
                        Log.e("CreateTransactionUseCase", "createTransaction - Response Body is null")
                        callback(false, null)
                    }
                } else {
                    Log.e("CreateTransactionUseCase", "createTransaction - Response not successful: ${response.code()} ${response.message()}")
                    callback(false, null)
                }
            }

            override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                Log.e("CreateTransactionUseCase", "createTransaction - onFailure: ${t.message}", t)
                callback(false, null)
            }
        })
    }
}