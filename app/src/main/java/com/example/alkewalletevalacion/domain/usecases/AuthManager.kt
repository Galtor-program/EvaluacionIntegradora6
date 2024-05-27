package com.example.alkewalletevalacion.domain.usecases

import com.example.alkewalletevalacion.domain.User

class AuthManager {
    private val usuariosRegistrados = mutableListOf<User>()

    fun usuarioRegistrado(user: User){
        usuariosRegistrados.add(user)
    }

    fun usuarioAutenticado(correo: String, password:String):Boolean {
        val user = usuariosRegistrados.find { it.correo == correo }
        return user?.passwrod == password
    }
}