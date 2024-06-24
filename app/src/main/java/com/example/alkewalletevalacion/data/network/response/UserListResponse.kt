package com.example.alkewalletevalacion.data.network.response

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    /**
     * Para llamar la data class como User
     */
    @SerializedName("users") val users: List<User>
)

data class User(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("roleId") val roleId: Int,
    @SerializedName("points") val points: Int
) {

    /**
     * Para usarlos en el Spinner
     */
    override fun toString(): String {
        return "$firstName $lastName"
    }
}