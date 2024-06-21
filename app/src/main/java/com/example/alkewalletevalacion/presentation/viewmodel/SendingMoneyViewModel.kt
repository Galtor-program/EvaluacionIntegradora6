package com.example.alkewalletevalacion.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.data.network.response.TransferRequest
import com.example.alkewalletevalacion.data.network.response.UserListResponse
import com.example.alkewalletevalacion.domain.usecases.AccountInfoUseCase
import com.example.alkewalletevalacion.domain.usecases.TransferUseCase
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase

class SendingMoneyViewModel(
    private val transferUseCase: TransferUseCase,
    private val userListUseCase: UserListUseCase,
    private val accountInfoUseCase: AccountInfoUseCase
) : ViewModel() {

    private val _usuariosList = MutableLiveData<List<UserListResponse>>()
    val usuariosList: LiveData<List<UserListResponse>> get() = _usuariosList

    val accountInfo = MutableLiveData<AccountResponse>()

    fun fetchUsuariosList() {
        userListUseCase.getUsers { success, users ->
            if (success) {
                _usuariosList.value = users?.take(5) // Tomar solo los primeros 5 usuarios
            } else {
                // Manejar el error
            }
        }
    }

    fun fetchAccountInfo() {
        accountInfoUseCase.getAccountInfo { success, accountResponseList ->
            if (success) {
                if (accountResponseList != null) {
                    accountInfo.value = accountResponseList.firstOrNull()
                }
            } else {
                // Manejar el error
            }
        }
    }

    fun makeTransfer(transferRequest: TransferRequest) {
        transferUseCase.transfer(transferRequest) { success ->
            if (success) {
                Log.d("SendingMoneyViewModel", "Transfer successful")
                fetchAccountInfo() // Actualizar la información de la cuenta después de la transferencia
            } else {
                Log.e("SendingMoneyViewModel", "Transfer failed")
                // Manejar el fallo de la transferencia
            }
        }
    }
}