package com.example.alkewalletevalacion.domain

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