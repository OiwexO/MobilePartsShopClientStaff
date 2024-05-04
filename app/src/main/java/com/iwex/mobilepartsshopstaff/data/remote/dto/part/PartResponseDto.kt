package com.iwex.mobilepartsshopstaff.data.remote.dto.part

import com.google.gson.annotations.SerializedName
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.device_type.DeviceTypeResponseDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.manufacturer.ManufacturerResponseDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.part_type.PartTypeResponseDto

data class PartResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("price") val price: Double,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("name") val name: String,
    @SerializedName("deviceModels") val deviceModels: List<String>,
    @SerializedName("specifications") val specifications: String,
    @SerializedName("manufacturer") val manufacturer: ManufacturerResponseDto,
    @SerializedName("deviceType") val deviceType: DeviceTypeResponseDto,
    @SerializedName("partType") val partType: PartTypeResponseDto
)