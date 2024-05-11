package com.iwex.mobilepartsshopstaff.data.remote.dto.part.part_type

import com.google.gson.annotations.SerializedName

data class PartTypeRequestDto(
    @SerializedName("nameEn") val nameEn: String,
    @SerializedName("nameUk") val nameUk: String
)
