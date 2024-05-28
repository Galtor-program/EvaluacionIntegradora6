package com.example.alkewalletevalacion.data.local

import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.data.model.Usuarios

class UsuariosDataSet {
    fun createUsuariosList(): MutableList<Usuarios> {
        return mutableListOf(
            Usuarios("Yara Khalil", "Oct 14, 10:24 AM", -15.00, R.drawable.profile1)
        )
    }
}