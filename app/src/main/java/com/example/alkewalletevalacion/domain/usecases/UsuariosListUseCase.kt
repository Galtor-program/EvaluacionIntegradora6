package com.example.alkewalletevalacion.domain.usecases

import com.example.alkewalletevalacion.data.local.UsuariosDataSet
import com.example.alkewalletevalacion.data.model.Usuarios

/**
 * Acá creamos la lista que pide que debe usar los atributos
 * definidos en el UsuarioDataSet
 */
class UsuariosListUseCase(private val usuariosDataSet: UsuariosDataSet) {
    fun getUsuariosList(): List<Usuarios> {
        return usuariosDataSet.createUsuariosList()
    }
}