package com.example.alkewalletevalacion.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


import com.example.alkewalletevalacion.data.network.response.UserResponse
import com.example.alkewalletevalacion.domain.usecases.AccountInfoUseCase
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.data.network.response.UserListResponse
import com.example.alkewalletevalacion.domain.usecases.UserInfoUseCase
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase

class HomeViewModel(
    private val userInfoUseCase: UserInfoUseCase,
    private val accountInfoUseCase: AccountInfoUseCase,
    private val userListUseCase: UserListUseCase
) : ViewModel() {

    private val TAG = "HomeViewModel"

    private val _userInfo = MutableLiveData<UserResponse?>()
    val userInfo: LiveData<UserResponse?> get() = _userInfo

    private val _accountInfo = MutableLiveData<List<AccountResponse>?>() // Cambiado a List<AccountResponse>
    val accountInfo: LiveData<List<AccountResponse>?> get() = _accountInfo

    private val _userList = MutableLiveData<List<UserListResponse>?>()
    val userList: LiveData<List<UserListResponse>?> get() = _userList


    fun fetchUserInfo() {
        userInfoUseCase.getUserInfo { success, userResponse ->
            if (success) {
                _userInfo.value = userResponse
                fetchAccountInfo()
            }
        }
    }

    private fun fetchAccountInfo() {
        accountInfoUseCase.getAccountInfo { success, accountResponse ->
            if (success) {
                _accountInfo.value = accountResponse
                Log.d(TAG, "fetchAccountInfo - AccountResponse List: $accountResponse")
            }
        }
    }

    fun fetchUserList() {
        userListUseCase.getUsers { success, userListResponse ->
            if (success) {
                Log.d(TAG, "fetchUserList - Success: $userListResponse")
                _userList.value = userListResponse
            } else {
                Log.e(TAG, "fetchUserList - Error")
            }
        }
    }
}