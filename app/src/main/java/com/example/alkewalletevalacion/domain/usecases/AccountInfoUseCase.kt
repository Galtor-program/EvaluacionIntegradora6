package com.example.alkewalletevalacion.domain.usecases

import android.util.Log
import com.example.alkewalletevalacion.data.network.api.AuthService
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountInfoUseCase(private val authService: AuthService) {

    fun getAccountInfo(callback: (Boolean, List<AccountResponse>?) -> Unit) {
        authService.getAccountInfo().enqueue(object : Callback<List<AccountResponse>> {
            override fun onResponse(call: Call<List<AccountResponse>>, response: Response<List<AccountResponse>>) {
                if (response.isSuccessful) {
                    callback(true, response.body())
                } else {
                    callback(false, null)
                }
            }

            override fun onFailure(call: Call<List<AccountResponse>>, t: Throwable) {
                Log.e("AccountInfoUseCase", "getAccountInfo onFailure: ${t.message}")
                callback(false, null)
            }
        })
    }

    suspend fun getAccountDetails(accountId: Int): AccountResponse? {
        return try {
            val response = authService.getAccountDetails(accountId)
            Log.d(TAG, "getAccountDetails - Success: AccountResponse: $response")
            response
        } catch (e: Exception) {
            Log.e(TAG, "getAccountDetails - Error: ${e.message}")
            null
        }
    }
}