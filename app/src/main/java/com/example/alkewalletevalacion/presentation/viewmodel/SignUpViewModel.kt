package com.example.alkewalletevalacion.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkewalletevalacion.domain.usecases.AuthUseCase


class SignUpViewModel(private val authUseCase: AuthUseCase) : ViewModel() {
    private val _navigateToLogin = MutableLiveData<Unit>()
    val navigateToLogin: LiveData<Unit>
        get() = _navigateToLogin

    fun registerUser(
        context: Context,
        nombre: String,
        apellido: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        if (authUseCase.registerUser(context, nombre, apellido, email, password, confirmPassword)) {
            _navigateToLogin.value = Unit
        }
    }


}