package com.example.alkewalletevalacion.domain.utils

import com.example.alkewalletevalacion.data.network.response.UserListResponse


object GlobalUserList {
    private var users: List<UserListResponse> = listOf()

    fun setUsers(newUsers: List<UserListResponse>) {
        users = newUsers
    }
    fun getUsers(): List<UserListResponse> {
        return users
    }

    fun getUserById(userId: Int): UserListResponse? {
        return users.find { it.id == userId }
    }
}