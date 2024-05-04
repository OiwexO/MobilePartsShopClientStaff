package com.iwex.mobilepartsshopstaff.data.remote.dto.part.manufacturer

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class ManufacturerRequestDto(
    @SerializedName("name") val name: String,
    @SerializedName("logo") val logo: MultipartBody.Part
)