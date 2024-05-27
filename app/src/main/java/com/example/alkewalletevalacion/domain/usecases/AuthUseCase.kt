package com.example.alkewalletevalacion.domain.usecases

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.alkewalletevalacion.domain.User


class AuthUseCase {
    companion object{
        private val registeredUsers = mutableListOf<User>()
    }


    fun registerUser(
        context: Context,
        nombre: String,
        apellido: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (password != confirmPassword) {
            Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!isValidEmail(email)) {
            Toast.makeText(context, "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show()
            return false
        }

        val user = User(nombre, apellido, email, password)
        registeredUsers.add(user)

        Log.d("AuthUseCase", "Usuario registrado: $user")

        return true
    }

    fun authenticateUser(context: Context, email: String, password: String): Boolean {
        Log.d("AuthUseCase", "Email recibido: $email, Password recibido: $password")
        val usuario = registeredUsers.find { it.email.trim() == email.trim() && it.password == password }

        Log.d("AuthUseCase", "Usuario encontrado: $usuario")

        if (usuario != null) {
            return true
        } else {
            Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}