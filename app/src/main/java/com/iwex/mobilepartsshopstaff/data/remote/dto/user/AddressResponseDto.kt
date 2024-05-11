package com.iwex.mobilepartsshopstaff.data.remote.dto.user

import com.google.gson.annotations.SerializedName

data class AddressResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("postalCode") val postalCode: Int,
    @SerializedName("country") val country: String,
    @SerializedName("state") val state: String,
    @SerializedName("city") val city: String,
    @SerializedName("street") val street: String,
    @SerializedName("buildingNumber") val buildingNumber: String
)