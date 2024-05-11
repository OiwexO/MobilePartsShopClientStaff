package com.iwex.mobilepartsshopstaff.data.remote.dto.part.manufacturer

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part

data class ManufacturerRequestDto(
    @Part("name") val name: RequestBody,
    @Part val logo: MultipartBody.Part
)