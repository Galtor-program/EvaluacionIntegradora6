package com.example.alkewalletevalacion.domain.createAccount

/**
 * Atributos del usuario cuando se registra.
 */
data class User(
   val nombre: String,
   val apellido: String,
   val email: String,
   val password: String
) {

}