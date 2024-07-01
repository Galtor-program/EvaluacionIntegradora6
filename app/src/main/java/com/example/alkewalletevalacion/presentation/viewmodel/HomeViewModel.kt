package com.example.alkewalletevalacion.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.example.alkewalletevalacion.data.network.response.UserResponse
import com.example.alkewalletevalacion.domain.usecases.AccountInfoUseCase
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.data.network.response.TransactionResponse
import com.example.alkewalletevalacion.domain.usecases.TransactionUseCase
import com.example.alkewalletevalacion.domain.usecases.UserInfoUseCase
import com.example.alkewalletevalacion.domain.utils.GlobalUserList
import kotlinx.coroutines.launch

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

    private val _accountDetailsMap = MutableLiveData<Map<Int, AccountResponse>>()
    val accountDetailsMap: LiveData<Map<Int, AccountResponse>> get() = _accountDetailsMap

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private val accountDetailsCache = mutableMapOf<Int, AccountResponse>()

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
                GlobalUserList.setAccounts(accountResponse ?: listOf()) // Actualiza GlobalUserList
            } else {
                _error.value = "Error fetching account info"
            }
        }
    }

    fun fetchTransactions() {
        transactionUseCase.getTransactions { success, transactionResponse ->
            if (success) {
                if (transactionResponse != null) {
                    _transactions.value = transactionResponse
                    Log.d(TAG, "fetchTransactions - TransactionResponse List: $transactionResponse")
                    fetchAccountDetailsForTransactions(transactionResponse)
                } else {
                    _error.value = "Error: Transaction response is null"
                    Log.e(TAG, "fetchTransactions - Success but transactionResponse is null")
                }
            } else {
                _error.value = "Error fetching transactions"
                Log.e(TAG, "fetchTransactions - Error fetching transactions")
            }
        }
    }

    private fun fetchAccountDetailsForTransactions(transactions: List<TransactionResponse>) {
        viewModelScope.launch {
            val accountDetailsMap = mutableMapOf<Int, AccountResponse>()
            transactions.forEach { transaction ->
                if (!accountDetailsCache.containsKey(transaction.toAccountId)) {
                    val accountDetails = accountInfoUseCase.getAccountDetails(transaction.toAccountId)
                    if (accountDetails != null) {
                        accountDetailsCache[transaction.toAccountId] = accountDetails
                        accountDetailsMap[transaction.toAccountId] = accountDetails
                        GlobalUserList.addAccount(accountDetails) // Añade la cuenta obtenida a GlobalUserList
                    } else {
                        Log.e(TAG, "Error fetching account details for accountId ${transaction.toAccountId}")
                    }
                } else {
                    accountDetailsMap[transaction.toAccountId] = accountDetailsCache[transaction.toAccountId]!!
                }
            }
            _accountDetailsMap.value = accountDetailsMap
        }
    }
}