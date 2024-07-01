package com.example.alkewalletevalacion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkewalletevalacion.domain.usecases.RegisterUserUseCase

class SignUpViewModelFactory(private val registerUserUseCase: RegisterUserUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(registerUserUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}