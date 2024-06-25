package com.example.alkewalletevalacion.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkewalletevalacion.data.network.response.TransactionRequest
import com.example.alkewalletevalacion.data.network.response.TransactionResponse

import com.example.alkewalletevalacion.data.network.response.UserListResponse
import com.example.alkewalletevalacion.domain.usecases.CreateTransactionUseCase
import com.example.alkewalletevalacion.domain.usecases.TransactionUseCase
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase


class SendingMoneyViewModel(
    private val userListUseCase: UserListUseCase,
    private val createTransactionUseCase: CreateTransactionUseCase,
    private val transactionUseCase: TransactionUseCase
) : ViewModel() {

    private val _usuariosList = MutableLiveData<List<UserListResponse>?>()
    val usuariosList: MutableLiveData<List<UserListResponse>?> get() = _usuariosList

    private val _transactionResult = MutableLiveData<Boolean>()
    val transactionResult: LiveData<Boolean> get() = _transactionResult

    private val _transactions = MutableLiveData<List<TransactionResponse>?>()
    val transactions: LiveData<List<TransactionResponse>?> get() = _transactions

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
            if (success) {
                // Fetch updated transactions after a successful transaction creation
                fetchTransactions()
            }
        }
    }

    fun fetchTransactions() {
        transactionUseCase.getTransactions { success, transactionResponse ->
            if (success) {
                if (transactionResponse != null) {
                    _transactions.value = transactionResponse
                } else {
                    Log.e("SendingMoneyViewModel", "fetchTransactions - Success but transactionResponse is null")
                }
            } else {
                Log.e("SendingMoneyViewModel", "fetchTransactions - Error fetching transactions")
            }
        }
    }
}