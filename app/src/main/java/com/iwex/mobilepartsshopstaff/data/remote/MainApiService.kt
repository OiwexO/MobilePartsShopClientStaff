package com.iwex.mobilepartsshopstaff.data.remote

import com.iwex.mobilepartsshopstaff.data.remote.ApiConstants.Companion.DEVICE_TYPES_MAPPING_V1
import com.iwex.mobilepartsshopstaff.data.remote.ApiConstants.Companion.MANUFACTURERS_MAPPING_V1
import com.iwex.mobilepartsshopstaff.data.remote.ApiConstants.Companion.PARTS_MAPPING_V1
import com.iwex.mobilepartsshopstaff.data.remote.ApiConstants.Companion.PART_TYPES_MAPPING_V1
import com.iwex.mobilepartsshopstaff.data.remote.ApiConstants.Companion.STAFFS_MAPPING_V1
import com.iwex.mobilepartsshopstaff.data.remote.dto.order.OrderResponseDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.PartResponseDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.device_type.DeviceTypeRequestDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.device_type.DeviceTypeResponseDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.manufacturer.ManufacturerResponseDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.part_type.PartTypeRequestDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.part_type.PartTypeResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
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
    suspend fun getManufacturer(@Path("manufacturerId") manufacturerId: Long): ManufacturerResponseDto

    @Multipart
    @POST(MANUFACTURERS_MAPPING_V1)
    suspend fun createManufacturer(
        @Part("name") name: RequestBody,
        @Part logo: MultipartBody.Part,
    ): ManufacturerResponseDto

    @Multipart
    @PUT("$MANUFACTURERS_MAPPING_V1/{manufacturerId}")
    suspend fun updateManufacturer(
        @Path("manufacturerId") manufacturerId: Long,
        @Part("name") name: RequestBody,
        @Part logo: MultipartBody.Part,
    ): ManufacturerResponseDto

    @DELETE("$MANUFACTURERS_MAPPING_V1/{manufacturerId}")
    suspend fun deleteManufacturer(@Path("manufacturerId") manufacturerId: Long)

    // part types

    @GET(PART_TYPES_MAPPING_V1)
    suspend fun getAllPartTypes(): List<PartTypeResponseDto>

    @GET("$PART_TYPES_MAPPING_V1/{partTypeId}")
    suspend fun getPartType(@Path("partTypeId") partTypeId: Long): PartTypeResponseDto

    @POST(PART_TYPES_MAPPING_V1)
    suspend fun createPartType(@Body request: PartTypeRequestDto): PartTypeResponseDto

    @PUT("$PART_TYPES_MAPPING_V1/{partTypeId}")
    suspend fun updatePartType(
        @Path("partTypeId") partTypeId: Long,
        @Body request: PartTypeRequestDto
    ): PartTypeResponseDto

    @DELETE("$PART_TYPES_MAPPING_V1/{partTypeId}")
    suspend fun deletePartType(@Path("partTypeId") partTypeId: Long)

    // parts

    @GET(PARTS_MAPPING_V1)
    suspend fun getAllParts(): List<PartResponseDto>

    @GET("$PARTS_MAPPING_V1/{partId}")
    suspend fun getPart(@Path("partId") partId: Long): PartResponseDto

    /*@POST(PARTS_MAPPING_V1)
    suspend fun createPart(@Body request: PartRequestDto): PartResponseDto*/
    @Multipart
    @POST(PARTS_MAPPING_V1)
    suspend fun createPart(
        @Part("price") price: RequestBody,
        @Part("quantity") quantity: RequestBody,
        @Part("name") name: RequestBody,
        @Part("deviceModels") deviceModels: List<@JvmSuppressWildcards RequestBody>,
        @Part("specifications") specifications: RequestBody,
        @Part("manufacturerId") manufacturerId: RequestBody,
        @Part("deviceTypeId") deviceTypeId: RequestBody,
        @Part("partTypeId") partTypeId: RequestBody,
        @Part image: MultipartBody.Part
    ): PartResponseDto

    @Multipart
    @PUT("$PARTS_MAPPING_V1/{partId}")
    suspend fun updatePart(
        @Path("partId") partId: Long,
        @Part("price") price: RequestBody,
        @Part("quantity") quantity: RequestBody,
        @Part("name") name: RequestBody,
        @Part("deviceModels") deviceModels: List<@JvmSuppressWildcards RequestBody>,
        @Part("specifications") specifications: RequestBody,
        @Part("manufacturerId") manufacturerId: RequestBody,
        @Part("deviceTypeId") deviceTypeId: RequestBody,
        @Part("partTypeId") partTypeId: RequestBody,
        @Part image: MultipartBody.Part
    ): PartResponseDto

    @DELETE("$PARTS_MAPPING_V1/{partId}")
    suspend fun deletePart(@Path("partId") partId: Long)

    // staffs

    @GET("$STAFFS_MAPPING_V1/orders/{staffId}")
    suspend fun getAssignedOrders(@Path("staffId") staffId: Long): List<OrderResponseDto>

    @GET("$STAFFS_MAPPING_V1/order/{orderId}")
    suspend fun getOrderById(@Path("orderId") orderId: Long): OrderResponseDto

    @PUT("$STAFFS_MAPPING_V1/order/{orderId}/status")
    suspend fun updateOrderStatus(@Path("orderId") orderId: Long): OrderResponseDto
}

