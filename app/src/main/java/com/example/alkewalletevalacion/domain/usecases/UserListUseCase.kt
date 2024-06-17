package com.example.alkewalletevalacion.domain.usecases

import android.util.Log
import com.example.alkewalletevalacion.data.network.api.AuthService
import com.example.alkewalletevalacion.data.network.response.UserListResponse
import com.example.alkewalletevalacion.data.network.response.UserListWrapper
import retrofit2.Call
import retrofit2.Callback


import retrofit2.Response
class UserListUseCase(private val authService: AuthService) {

    private val TAG = "UserListUseCase"

    fun getUsers(onResult: (Boolean, List<UserListResponse>?) -> Unit) {
        authService.getUsers().enqueue(object : Callback<UserListWrapper> {
            override fun onResponse(call: Call<UserListWrapper>, response: Response<UserListWrapper>) {
                if (response.isSuccessful) {
                    val userList = response.body()?.data
                    Log.d(TAG, "getUsers onResponse - Success: UserListResponse List: $userList")
                    if (userList == null) {
                        Log.d(TAG, "getUsers onResponse - userList is null")
                    } else {
                        userList.forEach { user ->
                            Log.d(TAG, "User: $user")
                        }
                    }
                    onResult(true, userList)
                } else {
                    Log.e(TAG, "getUsers onResponse - Error: ${response.code()}")
                    Log.e(TAG, "getUsers onResponse - Error Body: ${response.errorBody()?.string()}")
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<UserListWrapper>, t: Throwable) {
                Log.e(TAG, "getUsers onFailure: ${t.message}")
                onResult(false, null)
            }
        })
    }
}