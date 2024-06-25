package com.example.alkewalletevalacion.data.network.response

import com.google.gson.annotations.SerializedName

data class TransactionRequest(
    @SerializedName("amount") val amount: Int,
    @SerializedName("concept") val concept: String,
    @SerializedName("date") val date: String,
    @SerializedName("type") val type: String,
    @SerializedName("accountId") val accountId: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("to_account_id") val toAccountId: Int
)