package com.iwex.mobilepartsshopstaff.data.remote.dto.part

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class PartRequestDto(
    @SerializedName("price") val price: Double,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("name") val name: String,
    @SerializedName("deviceModels") val deviceModels: List<String>,
    @SerializedName("specifications") val specifications: String,
    @SerializedName("manufacturerId") val manufacturerId: Long,
    @SerializedName("deviceTypeId") val deviceTypeId: Long,
    @SerializedName("partTypeId") val partTypeId: Long,
    val partImage: MultipartBody.Part
)