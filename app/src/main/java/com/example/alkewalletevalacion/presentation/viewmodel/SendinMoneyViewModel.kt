package com.example.alkewalletevalacion.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.alkewalletevalacion.data.network.response.UserListResponse
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase


class SendingMoneyViewModel(private val userListUseCase: UserListUseCase) : ViewModel() {

    private val _usuariosList = MutableLiveData<List<UserListResponse>>()
    val usuariosList: LiveData<List<UserListResponse>> get() = _usuariosList

    fun fetchUsuariosList() {
        userListUseCase.getUsers { success, users ->
            if (success) {
                _usuariosList.value = users?.take(5) // Tomar solo los primeros 5 usuarios
            } else {
                // Manejar el error
            }
        }
    }
}