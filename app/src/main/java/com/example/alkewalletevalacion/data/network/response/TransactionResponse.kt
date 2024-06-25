package com.example.alkewalletevalacion.data.network.response

import com.google.gson.annotations.SerializedName


data class TransactionResponse(
    val id: Int,
    val amount: String,
    val concept: String,
    val date: String,
    val type: String,
    val accountId: Int,
    val userId: Int,
    val toAccountId: Int,
    val createdAt: String,
    val updatedAt: String
)