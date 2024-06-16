package com.example.alkewalletevalacion.domain.usecases

import android.util.Log
import com.example.alkewalletevalacion.data.network.api.AuthService
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountInfoUseCase(private val authService: AuthService) {

    private val TAG = "AccountInfoUseCase"

    fun getAccountInfo(onResult: (Boolean, List<AccountResponse>?) -> Unit) {
        authService.getAccountInfo().enqueue(object : Callback<List<AccountResponse>> {
            override fun onResponse(call: Call<List<AccountResponse>>, response: Response<List<AccountResponse>>) {
                if (response.isSuccessful) {
                    val accountList = response.body()
                    Log.d(TAG, "getAccountInfo onResponse - Success: AccountResponse List: $accountList")
                    onResult(true, accountList)
                } else {
                    Log.e(TAG, "getAccountInfo onResponse - Error: ${response.code()}")
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<List<AccountResponse>>, t: Throwable) {
                Log.e(TAG, "getAccountInfo onFailure: ${t.message}")
                onResult(false, null)
            }
        })
    }
}