package com.example.alkewalletevalacion.presentation.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


import com.example.alkewalletevalacion.data.network.response.UserResponse
import com.example.alkewalletevalacion.domain.usecases.AccountInfoUseCase
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.domain.usecases.UserInfoUseCase

class HomeViewModel(
    private val userInfoUseCase: UserInfoUseCase,
    private val accountInfoUseCase: AccountInfoUseCase
) : ViewModel() {

    private val _userInfo = MutableLiveData<UserResponse?>()
    val userInfo: LiveData<UserResponse?> get() = _userInfo

    private val _accountInfo = MutableLiveData<AccountResponse?>()
    val accountInfo: LiveData<AccountResponse?> get() = _accountInfo

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
            }
        }
    }
}