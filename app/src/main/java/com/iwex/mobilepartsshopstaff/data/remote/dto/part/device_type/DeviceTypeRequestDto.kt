package com.iwex.mobilepartsshopstaff.data.remote.dto.part.device_type

import com.google.gson.annotations.SerializedName

data class DeviceTypeRequestDto(
    @SerializedName("nameEn") val nameEn: String,
    @SerializedName("nameUk") val nameUk: String
)