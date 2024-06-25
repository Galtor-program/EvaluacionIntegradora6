package com.example.alkewalletevalacion.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


import com.example.alkewalletevalacion.data.network.response.UserResponse
import com.example.alkewalletevalacion.domain.usecases.AccountInfoUseCase
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.data.network.response.TransactionResponse
import com.example.alkewalletevalacion.domain.usecases.TransactionUseCase
import com.example.alkewalletevalacion.domain.usecases.UserInfoUseCase

class HomeViewModel(
    private val userInfoUseCase: UserInfoUseCase,
    private val accountInfoUseCase: AccountInfoUseCase,
    private val transactionUseCase: TransactionUseCase
) : ViewModel() {

    private val TAG = "HomeViewModel"

    private val _userInfo = MutableLiveData<UserResponse?>()
    val userInfo: LiveData<UserResponse?> get() = _userInfo

    private val _accountInfo = MutableLiveData<List<AccountResponse>?>()
    val accountInfo: LiveData<List<AccountResponse>?> get() = _accountInfo

    private val _transactions = MutableLiveData<List<TransactionResponse>?>()
    val transactions: MutableLiveData<List<TransactionResponse>?> get() = _transactions

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun fetchUserInfo() {
        userInfoUseCase.getUserInfo { success, userResponse ->
            if (success) {
                _userInfo.value = userResponse
                fetchAccountInfo()
                fetchTransactions()
            } else {
                _error.value = "Error fetching user info"
            }
        }
    }

    private fun fetchAccountInfo() {
        accountInfoUseCase.getAccountInfo { success, accountResponse ->
            if (success) {
                _accountInfo.value = accountResponse
                Log.d(TAG, "fetchAccountInfo - AccountResponse List: $accountResponse")
            } else {
                _error.value = "Error fetching account info"
            }
        }
    }

    private fun fetchTransactions() {
        transactionUseCase.getTransactions { success, transactionResponse ->
            if (success) {
                _transactions.value = transactionResponse
                Log.d(TAG, "fetchTransactions - TransactionResponse List: $transactionResponse")
            } else {
                _error.value = "Error fetching transactions"
            }
        }
    }
}