package com.example.alkewalletevalacion.presentation.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.alkewalletevalacion.data.model.Usuarios
import com.example.alkewalletevalacion.domain.usecases.UsuariosListUseCase

class HomeViewModel(private val usuariosListUseCase: UsuariosListUseCase) : ViewModel() {

    private val _usuariosList = MutableLiveData<List<Usuarios>>()
    val usuariosList: LiveData<List<Usuarios>> get() = _usuariosList

    fun fetchUsuariosList() {
        _usuariosList.value = usuariosListUseCase.getUsuariosList()
    }
}