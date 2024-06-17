package com.example.alkewalletevalacion.data.network.response

import com.google.gson.annotations.SerializedName

data class UserListWrapper(
    @SerializedName("data") val data: List<UserListResponse>
)