package com.example.alkewalletevalacion.data.local

import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.data.model.Usuarios

/**
 * Aca tendremos los datos de manera local que queremos que el recycleView
 * muestre
 */
class UsuariosDataSet {
    /**
     * Lista que proviene del model Usuarios
     */
    fun createUsuariosList(): MutableList<Usuarios> {
        return mutableListOf(
            Usuarios("Yara Khalil", "Oct 14, 10:24 AM", "-$15.00", R.drawable.profile1),
            Usuarios("Sarah Ibrahim", "Oct 12, 02:13 PM","+$20.50",R.drawable.profile2),
            Usuarios("Abraham Ibrahim","Oct 11, 01:19 AM","+$12.40",R.drawable.profile3),
            Usuarios("Reem Khaled","Oct 07, 09:10 PM","-$21.30",R.drawable.profile4),
            Usuarios("Hiba Saleh", "Oct 04, 05:45 AM","+$09.00",R.drawable.profile5)
        )
    }
}