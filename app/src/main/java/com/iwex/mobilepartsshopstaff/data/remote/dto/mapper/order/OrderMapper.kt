package com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.order

import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.ResponseMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.user.AddressMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.order.OrderResponseDto
import com.iwex.mobilepartsshopstaff.domain.entity.order.Order
import javax.inject.Inject

class OrderMapper @Inject constructor(
    private val orderItemMapper: OrderItemMapper,
    private val addressMapper: AddressMapper
) : ResponseMapper<Order, OrderResponseDto>() {

    override fun toEntity(dto: OrderResponseDto): Order {
        val orderItems = orderItemMapper.toEntityList(dto.orderItems)
        val shippingAddress = addressMapper.toEntity(dto.shippingAddress)
        return Order(
            id = dto.id,
            orderItems = orderItems,
            price = dto.price,
            status = dto.status,
            date = dto.date,
            customerId = dto.customerId,
            staffId = dto.staffId,
            shippingAddress = shippingAddress
        )
    }
}