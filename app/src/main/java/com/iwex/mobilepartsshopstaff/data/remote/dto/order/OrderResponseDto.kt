package com.iwex.mobilepartsshopstaff.data.remote.dto.order

import com.google.gson.annotations.SerializedName
import com.iwex.mobilepartsshopstaff.data.remote.dto.user.AddressResponseDto
import com.iwex.mobilepartsshopstaff.domain.entity.order.OrderStatus
import java.util.Date

data class OrderResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("orderItems") val orderItems: List<OrderItemResponseDto>,
    @SerializedName("price") val price: Double,
    @SerializedName("status") val status: OrderStatus,
    @SerializedName("date") val date: Date,
    @SerializedName("customerId") val customerId: Long,
    @SerializedName("staffId") val staffId: Long,
    @SerializedName("shippingAddress") val shippingAddress: AddressResponseDto
)