package com.example.alkewalletevalacion.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * LoginViewModel extiende de AndroidViewModel
 * subclase que retiene datos entre la aplicacion y la interfaz
 *
 */
class LoginViewModel(application: Application): AndroidViewModel(application) {
    /**
     * Objeto de tipo MutableLiveData contiene las unidades (Unit)
     * indicando que navegacion a la pantalla de resgistro debe ocurrir.
     */
    private val _navigationToSignUp = MutableLiveData<Unit>()
    val navigateToSignUp : LiveData<Unit>
        get() = _navigationToSignUp

    /**
     * Objeto de tipo MutableLiveData contiene las unidades (Unit)
     * indicando que navegacion a la pantalla de login debe ocurrir.
     */

    private val _navigateToPageLog = MutableLiveData<Unit>()
    val navigateToPageLog: LiveData<Unit>
        get() = _navigateToPageLog

    /**
     * activa al observador en el viewmodel, para que el fragmento navegue a la pantalla de
     * registro
     */
    fun onSingUpClicked(){
        _navigationToSignUp.value = Unit
    }

    /**
     * activa al observador en el viewmodel, para que el fragmento navegue a la pantalla de
     * Login
     */
    fun onAlreadyHaveAccountClicked(){
        _navigateToPageLog.value = Unit
    }


    /**
     * Clase interna para crear instancia de LoginViewModel
     * esta se asegura que solo se creen instancias del LoginViewModel
     * y no de otros ViewModels.
     */
    inner class Factory(private val application: Application) : ViewModelProvider.Factory {
        override  fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                /**
                 * Advertencia de tipo
                 */
                @Suppress("UNCHECKED_CAST")
                /**
                 * Crea una instancia de LoginViewModel usando application
                 * el objeto del tipo T es generico del ViewModel que se
                 * esta creando.
                 */
                return LoginViewModel(application) as T
            }
            throw IllegalArgumentException("Clase de ViewModel Desconocida")
        }
    }
}