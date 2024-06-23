package com.example.alkewalletevalacion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkewalletevalacion.domain.usecases.AccountInfoUseCase
import com.example.alkewalletevalacion.domain.usecases.TransactionsUseCase
import com.example.alkewalletevalacion.domain.usecases.UserInfoUseCase
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase

class HomeViewModelFactory(
    private val userInfoUseCase: UserInfoUseCase,
    private val accountInfoUseCase: AccountInfoUseCase,
    private val transactionsUseCase: TransactionsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(userInfoUseCase, accountInfoUseCase, transactionsUseCase ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}