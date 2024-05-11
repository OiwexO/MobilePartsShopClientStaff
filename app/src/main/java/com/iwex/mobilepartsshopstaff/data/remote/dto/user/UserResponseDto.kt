package com.iwex.mobilepartsshopstaff.data.remote.dto.user

import com.google.gson.annotations.SerializedName
import com.iwex.mobilepartsshopstaff.domain.entity.user.UserAuthority

data class UserResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("username") val username: String,
    @SerializedName("firstname") val firstname: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("authority") val authority: UserAuthority,
)
