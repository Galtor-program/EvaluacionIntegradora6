package com.example.alkewalletevalacion.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


import com.example.alkewalletevalacion.data.network.response.UserResponse
import com.example.alkewalletevalacion.domain.usecases.AccountInfoUseCase
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.data.network.response.TransactionResponse
import com.example.alkewalletevalacion.data.network.response.UserListResponse
import com.example.alkewalletevalacion.domain.usecases.TransactionsUseCase
import com.example.alkewalletevalacion.domain.usecases.UserInfoUseCase
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase

class HomeViewModel(
    private val userInfoUseCase: UserInfoUseCase,
    private val accountInfoUseCase: AccountInfoUseCase,
    private val transactionsUseCase: TransactionsUseCase
) : ViewModel() {

    val userInfo = MutableLiveData<UserResponse?>()
    val accountInfo = MutableLiveData<List<AccountResponse>?>()
    val transactions = MutableLiveData<List<TransactionResponse>?>()

    fun fetchUserInfo() {
        userInfoUseCase.getUserInfo { success, userInfoResponse ->
            if (success) {
                userInfo.value = userInfoResponse
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

    fun fetchTransactions() {
        transactionsUseCase.getTransactions { success, transactionsResponse ->
            if (success) {
                transactions.value = transactionsResponse
            }
        }
    }
}