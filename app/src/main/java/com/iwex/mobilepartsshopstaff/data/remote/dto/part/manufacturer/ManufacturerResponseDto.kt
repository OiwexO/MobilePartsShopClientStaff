package com.iwex.mobilepartsshopstaff.data.remote.dto.part.manufacturer

import com.google.gson.annotations.SerializedName

data class ManufacturerResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String
)