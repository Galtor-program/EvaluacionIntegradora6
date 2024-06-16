package com.example.alkewalletevalacion.domain.usecases

import android.util.Log
import com.example.alkewalletevalacion.data.network.api.AuthService
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountInfoUseCase(private val authService: AuthService) {

    private val TAG = "AccountInfoUseCase"

    fun getAccountInfo(onResult: (Boolean, AccountResponse?) -> Unit) {
        authService.getAccountInfo().enqueue(object : Callback<AccountResponse?> {
            override fun onResponse(call: Call<AccountResponse?>, response: Response<AccountResponse?>) {
                if (response.isSuccessful) {
                    val accountResponse = response.body()
                    Log.d(TAG, "getAccountInfo onResponse - Success: AccountResponse: $accountResponse")
                    onResult(true, accountResponse)
                } else {
                    Log.e(TAG, "getAccountInfo onResponse - Error: ${response.code()}")
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<AccountResponse?>, t: Throwable) {
                Log.e(TAG, "getAccountInfo onFailure: ${t.message}")
                onResult(false, null)
            }
        })
    }
}