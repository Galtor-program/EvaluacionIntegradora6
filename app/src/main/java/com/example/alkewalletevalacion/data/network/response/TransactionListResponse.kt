package com.example.alkewalletevalacion.data.network.response

data class TransactionListResponse(
    val previousPage: String?,
    val nextPage: String?,
    val data: List<TransactionResponse>
)