package com.iwex.mobilepartsshopstaff.domain.model.order

import com.iwex.mobilepartsshopstaff.domain.model.part.Part

data class OrderItem(
    val id: Long,
    val orderId: Long,
    val part: Part,
    val quantity: Int
)
