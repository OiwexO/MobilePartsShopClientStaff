package com.iwex.mobilepartsshopstaff.data.remote

import com.iwex.mobilepartsshopstaff.data.remote.ApiConstants.Companion.DEVICE_TYPES_MAPPING_V1
import com.iwex.mobilepartsshopstaff.data.remote.ApiConstants.Companion.MANUFACTURERS_MAPPING_V1
import com.iwex.mobilepartsshopstaff.data.remote.ApiConstants.Companion.PARTS_MAPPING_V1
import com.iwex.mobilepartsshopstaff.data.remote.ApiConstants.Companion.PART_TYPES_MAPPING_V1
import com.iwex.mobilepartsshopstaff.data.remote.ApiConstants.Companion.STAFFS_MAPPING_V1
import com.iwex.mobilepartsshopstaff.data.remote.dto.order.OrderResponseDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.PartRequestDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.PartResponseDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.device_type.DeviceTypeRequestDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.device_type.DeviceTypeResponseDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.manufacturer.ManufacturerRequestDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.manufacturer.ManufacturerResponseDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.part_type.PartTypeRequestDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.part_type.PartTypeResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MainApiService {

    // device types

    @GET(DEVICE_TYPES_MAPPING_V1)
    suspend fun getAllDeviceTypes(): List<DeviceTypeResponseDto>

    @GET("$DEVICE_TYPES_MAPPING_V1/{deviceTypeId}")
    suspend fun getDeviceType(@Path("deviceTypeId") deviceTypeId: Long): DeviceTypeResponseDto

    @POST(DEVICE_TYPES_MAPPING_V1)
    suspend fun createDeviceType(@Body request: DeviceTypeRequestDto): DeviceTypeResponseDto

    @PUT("$DEVICE_TYPES_MAPPING_V1/{deviceTypeId}")
    suspend fun updateDeviceType(
        @Path("deviceTypeId") deviceTypeId: Long,
        @Body request: DeviceTypeRequestDto
    ): DeviceTypeResponseDto

    @DELETE("$DEVICE_TYPES_MAPPING_V1/{deviceTypeId}")
    suspend fun deleteDeviceType(@Path("deviceTypeId") deviceTypeId: Long)

    // manufacturers

    @GET(MANUFACTURERS_MAPPING_V1)
    suspend fun getAllManufacturers(): List<ManufacturerResponseDto>

    @GET("$MANUFACTURERS_MAPPING_V1/{manufacturerId}")
    fun getManufacturer(@Path("manufacturerId") manufacturerId: Long): ManufacturerResponseDto

    @Multipart
    @POST(MANUFACTURERS_MAPPING_V1)
    fun createManufacturer(
        @Body request: ManufacturerRequestDto
    ): ManufacturerResponseDto

    @Multipart
    @PUT("$MANUFACTURERS_MAPPING_V1/{manufacturerId}")
    fun updateManufacturer(
        @Path("manufacturerId") manufacturerId: Long,
        @Body request: ManufacturerRequestDto
    ): ManufacturerResponseDto

    @DELETE("$MANUFACTURERS_MAPPING_V1/{manufacturerId}")
    fun deleteManufacturer(@Path("manufacturerId") manufacturerId: Long): Call<Void>

    // part types

    @GET(PART_TYPES_MAPPING_V1)
    fun getAllPartTypes(): List<PartTypeResponseDto>

    @GET("$PART_TYPES_MAPPING_V1/{partTypeId}")
    fun getPartType(@Path("partTypeId") partTypeId: Long): PartTypeResponseDto

    @POST(PART_TYPES_MAPPING_V1)
    fun createPartType(@Body request: PartTypeRequestDto): PartTypeResponseDto

    @PUT("$PART_TYPES_MAPPING_V1/{partTypeId}")
    fun updatePartType(
        @Path("partTypeId") partTypeId: Long,
        @Body request: PartTypeRequestDto
    ): PartTypeResponseDto

    @DELETE("$PART_TYPES_MAPPING_V1/{partTypeId}")
    fun deletePartType(@Path("partTypeId") partTypeId: Long)

    // parts

    @GET(PARTS_MAPPING_V1)
    fun getAllParts(): List<PartResponseDto>

    @GET("$PARTS_MAPPING_V1/{partId}")
    fun getPart(@Path("partId") partId: Long): PartResponseDto

    @POST(PARTS_MAPPING_V1)
    fun createPart(@Body request: PartRequestDto): PartResponseDto

    @PUT("$PARTS_MAPPING_V1/{partId}")
    fun updatePart(@Path("partId") partId: Long, @Body request: PartRequestDto): PartResponseDto

    @DELETE("$PARTS_MAPPING_V1/{partId}")
    fun deletePart(@Path("partId") partId: Long)

    // staffs

    @GET("$STAFFS_MAPPING_V1/{staffId}/orders")
    fun getAssignedOrders(@Path("staffId") staffId: Long): List<OrderResponseDto>

    @PUT("$STAFFS_MAPPING_V1/order/{orderId}/status")
    fun updateOrderStatus(@Path("orderId") orderId: Long): OrderResponseDto
}

