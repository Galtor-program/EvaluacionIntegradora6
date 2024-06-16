package com.example.alkewalletevalacion.data.network.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("roleId") val roleId: Int?,
    @SerializedName("points") val points: Int?
)

