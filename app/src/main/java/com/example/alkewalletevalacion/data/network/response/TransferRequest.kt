package com.example.alkewalletevalacion.data.network.response

data class TransferRequest(
    val amount: Int,
    val concept: String,
    val date: String,
    val type: String,
    val accountId: Int,
    val userId: Int,
    val to_account_id: Int
)
