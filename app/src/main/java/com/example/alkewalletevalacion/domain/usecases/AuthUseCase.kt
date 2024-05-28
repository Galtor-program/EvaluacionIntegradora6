package com.example.alkewalletevalacion.domain.usecases

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.alkewalletevalacion.domain.User

/**
 * Clare para autenticacion de usuarios
 */
class AuthUseCase {
    /**
     * Se realiza un companion object para ser compartido por
     * las instancias de la clase sin tener que crear
     * una instancia de AuthUseCase
     */
    companion object{
        private val registeredUsers = mutableListOf<User>()
    }

    /**
     * Método para registrar nuevos usuarios
     */
    fun registerUser(
        context: Context,
        nombre: String,
        apellido: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        /**
         * Verifica que las 2 password ingresads coincidan
         */
        if (password != confirmPassword) {
            Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }
        /**
         * !isValidEmail usa una expresión regular para
         * verificar si el usuario esta ingresando un correo electrónico
         *
         */
        if (!isValidEmail(email)) {
            Toast.makeText(context, "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show()
            return false
        }

        val user = User(nombre, apellido, email, password)
        registeredUsers.add(user)

        Log.d("AuthUseCase", "Usuario registrado: $user")

        return true
    }

    /**
     * Aca verificamos si el usuario es el correcto
     * utilizamos trim para normalizar las mayúsculas y/o espacios al momento de
     * leer los datos.
     */
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

    /**
     * Aca esta la expresión regular del correo
     */
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}