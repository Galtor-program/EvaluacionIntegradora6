package com.example.alkewalletevalacion.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkewalletevalacion.domain.usecases.AuthUseCase

class PageLogViewModel (application: Application, private val authUseCase: AuthUseCase): AndroidViewModel(application){
    /**
     * Con este live Data navegamos hacia el registro
     */
    private val _navigateToSignUp = MutableLiveData<Unit>()
    val navigateToSignUp: LiveData<Unit>
        get() = _navigateToSignUp

    /**
     * Con este liveData navegamos hacia el Home
     */
    private val _navigationToHome = MutableLiveData<Unit>()
    val navigationToHome: LiveData<Unit>
        get() = _navigationToHome

    /**
     * Al hacer click en el inicio de sesion pasamos el correo y el password a la funcion
     * se genera un log para revisar si los datos vienen como corresponde
     * llamamos al método de autenticar usuario desde el AuthUseCase
     * Si el logueo es correcto pasamos hacia el HomeFragment.
     */
    fun onLoginClick(email: String, password: String) {
        Log.d("PageLogViewModel", "Email recibido en ViewModel: $email, Password recibido en ViewModel: $password")
        if (authUseCase.authenticateUser(getApplication(), email, password)) {
            _navigationToHome.value = Unit
        }
    }

    /**
     * Aca navegamos hacia el Fragmento para crear cuenta.
     */
    fun onSignUpClicked() {
        _navigateToSignUp.value = Unit
    }

    /**
     * Clase  factory
     */
    class Factory(private val application: Application, private val authUseCase: AuthUseCase) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PageLogViewModel::class.java)) {
                return PageLogViewModel(application, authUseCase) as T
            }
            throw IllegalArgumentException("Clase de ViewModel Desconocida")
        }
    }
}