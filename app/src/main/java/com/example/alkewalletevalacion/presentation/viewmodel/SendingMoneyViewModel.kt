package com.example.alkewalletevalacion.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.data.network.response.TransactionResponse
import com.example.alkewalletevalacion.data.network.response.TransferRequest
import com.example.alkewalletevalacion.data.network.response.UserListResponse
import com.example.alkewalletevalacion.domain.usecases.AccountInfoUseCase
import com.example.alkewalletevalacion.domain.usecases.TransactionsUseCase
import com.example.alkewalletevalacion.domain.usecases.TransferUseCase
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase
import kotlinx.coroutines.withContext

class SendingMoneyViewModel(
    private val transferUseCase: TransferUseCase,
    private val userListUseCase: UserListUseCase,
    private val accountInfoUseCase: AccountInfoUseCase
) : ViewModel() {

    val userList = MutableLiveData<List<UserListResponse>?>()
    val accountInfo = MutableLiveData<List<AccountResponse>?>()

    fun fetchUserList() {
        userListUseCase.getUsers { success, userListResponse ->
            if (success) {
                userList.value = userListResponse
            }
        }
    }

    fun fetchAccountInfo() {
        accountInfoUseCase.getAccountInfo { success, accountResponse ->
            if (success) {
                accountInfo.value = accountResponse
            }
        }
    }

    fun makeTransfer(request: TransferRequest) {
        transferUseCase.transfer(request) { success ->
            if (success) {
                Log.d("SendingMoneyViewModel", "Transfer successful")
                fetchAccountInfo()
            } else {
                Log.e("SendingMoneyViewModel", "Transfer failed")
            }
        }
    }

    fun depositOrTransfer(accountId: Int, request: TransferRequest) {
        transferUseCase.depositOrTransfer(accountId, request) { success ->
            if (success) {
                Log.d("SendingMoneyViewModel", "Transfer successful")
            } else {
                Log.e("SendingMoneyViewModel", "Transfer failed")
            }
        }
    }
}