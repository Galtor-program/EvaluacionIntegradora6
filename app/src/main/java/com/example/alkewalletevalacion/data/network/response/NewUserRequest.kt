package com.example.alkewalletevalacion.data.network.response

data class NewUserRequest(
    val first_name: String,
    val last_name: String,
    val email: String,
    val password: String,
    val roleId: Int = 1,
    val points: Int
)