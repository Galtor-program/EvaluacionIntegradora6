package com.example.alkewalletevalacion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkewalletevalacion.domain.usecases.AccountInfoUseCase
import com.example.alkewalletevalacion.domain.usecases.UserInfoUseCase
import com.example.alkewalletevalacion.domain.usecases.TransactionUseCase

class HomeViewModelFactory(
    private val userInfoUseCase: UserInfoUseCase,
    private val accountInfoUseCase: AccountInfoUseCase,
    private val transactionUseCase: TransactionUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(userInfoUseCase, accountInfoUseCase, transactionUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}