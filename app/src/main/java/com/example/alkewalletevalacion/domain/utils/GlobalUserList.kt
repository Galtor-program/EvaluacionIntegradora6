package com.example.alkewalletevalacion.domain.utils


import com.example.alkewalletevalacion.data.network.response.AccountResponse
import com.example.alkewalletevalacion.data.network.response.TransactionResponse
import com.example.alkewalletevalacion.data.network.response.UserListResponse


object GlobalUserList {
    private var users: List<UserListResponse> = listOf()
    private var accounts: List<AccountResponse> = listOf()

    fun setUsers(newUsers: List<UserListResponse>) {
        users = newUsers
    }

    fun setAccounts(newAccounts: List<AccountResponse>) {
        accounts = newAccounts
    }

    fun getUserById(userId: Int): UserListResponse? {
        return users.find { it.id == userId }
    }

    fun getAccountById(accountId: Int): AccountResponse? {
        return accounts.find { it.id == accountId }
    }
}