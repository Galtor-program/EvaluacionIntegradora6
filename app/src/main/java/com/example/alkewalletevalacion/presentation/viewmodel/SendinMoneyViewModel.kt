package com.example.alkewalletevalacion.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkewalletevalacion.data.network.api.AuthService
import com.example.alkewalletevalacion.data.network.response.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SendingMoneyViewModel(private val authService: AuthService) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchUsers() {
        authService.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val userList = response.body()
                    if (userList != null) {
                        _users.value = userList.take(5) // Tomar los primeros 5 usuarios
                    }
                } else {
                    _error.value = "Error: ${response.code()} ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                _error.value = "Error: ${t.message}"
            }
        })
    }
}