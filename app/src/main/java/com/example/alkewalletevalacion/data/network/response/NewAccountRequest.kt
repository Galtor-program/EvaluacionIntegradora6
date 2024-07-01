package com.example.alkewalletevalacion.data.network.response

data class NewAccountRequest(
    val creationDate: String,
    val money: Int,
    val isBlocked: Boolean,
    val userId: Int
)