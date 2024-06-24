package com.example.alkewalletevalacion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkewalletevalacion.data.network.api.AuthService

class SendingMoneyViewModelFactory(
    private val authService: AuthService
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SendingMoneyViewModel::class.java)) {
            return SendingMoneyViewModel(authService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}