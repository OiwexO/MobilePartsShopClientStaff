package com.iwex.mobilepartsshopstaff.domain.entity.order

import com.iwex.mobilepartsshopstaff.domain.entity.part.Part

data class OrderItem(
    val id: Long,
    val orderId: Long,
    val part: Part,
    val quantity: Int,
)
