package com.iwex.mobilepartsshopstaff.data.remote.dto.part

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.RequestBody

data class PartRequestDto(
    @SerializedName("price") val price: RequestBody,
    @SerializedName("quantity") val quantity: RequestBody,
    @SerializedName("name") val name: RequestBody,
    @SerializedName("deviceModels") val deviceModels: List<RequestBody>,
    @SerializedName("specifications") val specifications: RequestBody,
    @SerializedName("manufacturerId") val manufacturerId: RequestBody,
    @SerializedName("deviceTypeId") val deviceTypeId: RequestBody,
    @SerializedName("partTypeId") val partTypeId: RequestBody,
    val partImage: MultipartBody.Part
)