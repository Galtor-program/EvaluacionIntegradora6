package com.example.alkewalletevalacion.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkewalletevalacion.data.network.response.LoginRequest
import com.example.alkewalletevalacion.domain.usecases.AuthUseCase

class PageLogViewModel(private val authUseCase: AuthUseCase, application: Application) : AndroidViewModel(application) {

    val navigationToHome = MutableLiveData<Unit>()

    fun onLoginClick(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        authUseCase.authenticateUser(loginRequest) { success, accessToken ->
            if (success) {
                // Guardar el token en SharedPreferences
                val sharedPreferences = getApplication<Application>().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                sharedPreferences.edit().putString("accessToken", accessToken).apply()

                navigationToHome.value = Unit
            } else {
                Log.e("PageLogViewModel", "Error en autenticación: No se pudo iniciar sesión.")
            }
        }
    }

    class Factory(private val application: Application, private val authUseCase: AuthUseCase) : ViewModelProvider.AndroidViewModelFactory(application) {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PageLogViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PageLogViewModel(authUseCase, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}