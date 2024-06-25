package com.example.alkewalletevalacion.domain.usecases

import com.example.alkewalletevalacion.data.network.api.AuthService
import com.example.alkewalletevalacion.data.network.response.TransactionRequest
import com.example.alkewalletevalacion.data.network.response.TransactionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateTransactionUseCase(private val authService: AuthService) {
    fun createTransaction(transactionRequest: TransactionRequest, callback: (Boolean, TransactionResponse?) -> Unit) {
        authService.createTransaction(transactionRequest).enqueue(object :
            Callback<TransactionResponse> {
            override fun onResponse(call: Call<TransactionResponse>, response: Response<TransactionResponse>) {
                if (response.isSuccessful) {
                    callback(true, response.body())
                } else {
                    callback(false, null)
                }
            }

            override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                callback(false, null)
            }
        })
    }
}