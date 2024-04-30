package com.iwex.mobilepartsshopstaff.domain.model.order

import com.iwex.mobilepartsshopstaff.domain.model.user.Address
import java.util.Date

data class Order(
    val id: Long,
    val orderItems: List<OrderItem>,
    val price: Double,
    val status: OrderStatus,
    val date: Date,
    val customerId: Long,
    val staffId: Long,
    val shippingAddress: Address
)
