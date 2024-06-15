package com.example.alkewalletevalacion.data.network.response

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(
    @SerializedName("accessToken") val accessToken:String
) {
}