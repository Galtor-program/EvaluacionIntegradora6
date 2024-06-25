package com.example.alkewalletevalacion.data.network.response

import com.google.gson.annotations.SerializedName

data class UserListWrapper(
    @SerializedName("previousPage") val previousPage: String?,
    @SerializedName("nextPage") val nextPage: String?,
    @SerializedName("data") val data: List<UserListResponse>
)