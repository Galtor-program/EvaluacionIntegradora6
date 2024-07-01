package com.example.alkewalletevalacion.domain.usecases

import android.util.Log

import com.example.alkewalletevalacion.data.network.api.AuthService
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.data.network.response.TransactionListResponse
import com.example.alkewalletevalacion.data.network.response.TransactionResponse


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionUseCase(private val authService: AuthService) {
    fun getTransactions(callback: (Boolean, List<TransactionResponse>?) -> Unit) {
        authService.getTransactions().enqueue(object : Callback<TransactionListResponse> {
            override fun onResponse(
                call: Call<TransactionListResponse>,
                response: Response<TransactionListResponse>
            ) {
                if (response.isSuccessful) {
                    val transactionListResponse = response.body()
                    if (transactionListResponse != null) {
                        Log.d("TransactionUseCase", "getTransactions - onResponse: ${transactionListResponse.data}")
                        callback(true, transactionListResponse.data)
                    } else {
                        Log.e("TransactionUseCase", "getTransactions - Success but transactionListResponse is null")
                        callback(true, null)
                    }
                } else {
                    Log.e("TransactionUseCase", "getTransactions - Unsuccessful response")
                    callback(false, null)
                }
            }


            override fun onFailure(call: Call<TransactionListResponse>, t: Throwable) {
                Log.e("TransactionUseCase", "getTransactions - onFailure: ${t.message}", t)
                callback(false, null)
            }
        })
    }
}