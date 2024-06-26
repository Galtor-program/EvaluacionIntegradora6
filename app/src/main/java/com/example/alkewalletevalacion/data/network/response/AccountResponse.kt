package com.example.alkewalletevalacion.data.network.response

import com.google.gson.annotations.SerializedName

data class AccountResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("creationDate") val creationDate: String?,
    @SerializedName("money") val money: Int?,
    @SerializedName("isBlocked") val isBlocked: Boolean?,
    @SerializedName("userId") val userId: Int?
)