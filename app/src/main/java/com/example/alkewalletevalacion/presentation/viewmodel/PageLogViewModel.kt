package com.example.alkewalletevalacion.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkewalletevalacion.data.network.response.LoginRequest
import com.example.alkewalletevalacion.domain.usecases.AuthUseCase

class PageLogViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    // Método para iniciar sesión
    fun onLoginClick(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        authUseCase.authenticateUser(loginRequest) { success, accessToken ->
            if (success) {
                // Iniciar navegación hacia HomeFragment si el inicio de sesión fue exitoso
                navigationToHome.value = Unit
            } else {
                Log.e("PageLogViewModel", "Error en autenticación: No se pudo iniciar sesión.")
                // Manejar el error de inicio de sesión aquí si es necesario
            }
        }
    }

    // LiveData para navegar hacia el HomeFragment
    val navigationToHome = MutableLiveData<Unit>()

    // Factory para el ViewModel
    class Factory(private val application: Application, private val authUseCase: AuthUseCase) : ViewModelProvider.AndroidViewModelFactory(application) {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PageLogViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PageLogViewModel(authUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}