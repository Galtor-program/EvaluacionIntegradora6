package com.example.alkewalletevalacion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkewalletevalacion.domain.usecases.CreateTransactionUseCase
import com.example.alkewalletevalacion.domain.usecases.UserInfoUseCase
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase

class SendingMoneyViewModelFactory(
    private val userListUseCase: UserListUseCase,
    private val createTransactionUseCase: CreateTransactionUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SendingMoneyViewModel::class.java)) {
            return SendingMoneyViewModel(userListUseCase, createTransactionUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}