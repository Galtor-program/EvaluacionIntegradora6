package com.example.alkewalletevalacion.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alkewalletevalacion.data.network.api.AuthService
import com.example.alkewalletevalacion.data.network.response.TransactionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionUseCase(private val authService: AuthService) {
    fun getTransactions(callback: (Boolean, List<TransactionResponse>?) -> Unit) {
        authService.getTransactions().enqueue(object : Callback<List<TransactionResponse>> {
            override fun onResponse(call: Call<List<TransactionResponse>>, response: Response<List<TransactionResponse>>) {
                if (response.isSuccessful) {
                    callback(true, response.body())
                } else {
                    callback(false, null)
                }
            }

            override fun onFailure(call: Call<List<TransactionResponse>>, t: Throwable) {
                callback(false, null)
            }
        })
    }
}