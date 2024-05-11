package com.iwex.mobilepartsshopstaff.data.remote.dto.authentication

import com.google.gson.annotations.SerializedName

data class AuthenticationRequestDto(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
)