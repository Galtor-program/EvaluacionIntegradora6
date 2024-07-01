package com.example.alkewalletevalacion.domain.utils


import android.util.Log
import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.data.network.response.TransactionResponse
import com.example.alkewalletevalacion.data.network.response.UserListResponse


object GlobalUserList {
    private var users: List<UserListResponse> = listOf()
    private var accounts: MutableList<AccountResponse> = mutableListOf()

    fun setUsers(newUsers: List<UserListResponse>) {
        users = newUsers
        Log.d("GlobalUserList", "Users set: $users")
    }

    fun setAccounts(newAccounts: List<AccountResponse>) {
        accounts = newAccounts.toMutableList()
        Log.d("GlobalUserList", "Accounts set: $accounts")
    }

    fun addAccount(newAccount: AccountResponse) {
        if (accounts.none { it.id == newAccount.id }) {
            accounts.add(newAccount)
            Log.d("GlobalUserList", "Account added: $newAccount")
        }
    }

    fun getUserById(userId: Int): UserListResponse? {
        return users.find { it.id == userId }
    }

    fun getAccountById(accountId: Int): AccountResponse? {
        return accounts.find { it.id == accountId }
    }
}