package com.example.alkewalletevalacion.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkewalletevalacion.data.network.response.TransactionRequest

import com.example.alkewalletevalacion.data.network.response.UserListResponse
import com.example.alkewalletevalacion.domain.usecases.CreateTransactionUseCase
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase


class SendingMoneyViewModel(
    private val userListUseCase: UserListUseCase,
    private val createTransactionUseCase: CreateTransactionUseCase
) : ViewModel() {

    private val _usuariosList = MutableLiveData<List<UserListResponse>?>()
    val usuariosList: MutableLiveData<List<UserListResponse>?> get() = _usuariosList

    private val _transactionResult = MutableLiveData<Boolean>()
    val transactionResult: LiveData<Boolean> get() = _transactionResult

    fun fetchUsuariosList() {
        userListUseCase.getUsers { success, userList ->
            if (success) {
                _usuariosList.value = userList
            }
        }
    }

    fun createTransaction(transactionRequest: TransactionRequest) {
        createTransactionUseCase.createTransaction(transactionRequest) { success, _ ->
            _transactionResult.value = success
        }
    }
}