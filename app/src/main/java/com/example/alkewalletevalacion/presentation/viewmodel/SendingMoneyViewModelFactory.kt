package com.example.alkewalletevalacion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkewalletevalacion.domain.usecases.AccountInfoUseCase
import com.example.alkewalletevalacion.domain.usecases.TransferUseCase
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase


class SendingMoneyViewModelFactory(
    private val transferUseCase: TransferUseCase,
    private val userListUseCase: UserListUseCase,
    private val accountInfoUseCase: AccountInfoUseCase

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SendingMoneyViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return SendingMoneyViewModel(transferUseCase, userListUseCase, accountInfoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}