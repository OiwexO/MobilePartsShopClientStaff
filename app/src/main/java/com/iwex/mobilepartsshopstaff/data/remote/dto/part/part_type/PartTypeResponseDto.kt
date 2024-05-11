package com.iwex.mobilepartsshopstaff.data.remote.dto.part.part_type

import com.google.gson.annotations.SerializedName

data class PartTypeResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("nameEn") val nameEn: String,
    @SerializedName("nameUk") val nameUk: String
)
