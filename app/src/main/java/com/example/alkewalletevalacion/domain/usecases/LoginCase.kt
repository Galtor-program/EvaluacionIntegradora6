package com.example.alkewalletevalacion.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginCase: ViewModel() {
    private val _loginSucess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSucess

    /**
     * funcion que recibe el correo y el password enviados desde
     * PageLogFragment, devolveremos el valor verdadero
     * si es email es igual al string de user, y si password es igual
     * al string de pass
     */
    fun loginUser(email:String, password:String){
        val user = "prueba@prueba.cl"
        val pass = "123456"
        if (email == user && password == pass){
            _loginSucess.value=true
        }else{
            _loginSucess.value= false
        }
    }
}