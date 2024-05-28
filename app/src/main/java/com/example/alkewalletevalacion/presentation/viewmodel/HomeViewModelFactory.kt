package com.example.alkewalletevalacion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkewalletevalacion.domain.usecases.UsuariosListUseCase

class HomeViewModelFactory(private val usuariosListUseCase: UsuariosListUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(usuariosListUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}