package com.example.alkewalletevalacion.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageLogViewModel (application: Application): AndroidViewModel(application){
    private val _navigateToSignUp = MutableLiveData<Unit>()
    val navigateToSignUp : LiveData<Unit>
        get() = _navigateToSignUp

    private val _navigationToHome = MutableLiveData<Unit>()
    val navigationToHome: LiveData<Unit>
        get() = _navigationToHome


    fun onLoginClick(){
        _navigationToHome.value = Unit
    }

    fun onSignUpClicked(){
        _navigateToSignUp.value = Unit
    }


    inner class Factory(private val application: Application): ViewModelProvider.Factory{
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(PageLogViewModel::class.java)){

                return PageLogViewModel(application) as T
            }
            throw IllegalArgumentException("Clase de ViewModel Desconocida")

        }
    }

}