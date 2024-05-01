package com.iwex.mobilepartsshopstaff.domain.use_case.order

import com.iwex.mobilepartsshopstaff.domain.entity.order.Order
import com.iwex.mobilepartsshopstaff.domain.repository.order.OrderRepository
import javax.inject.Inject

class UpdateOrderStatusUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(orderId: Long): Result<Order> {
        return orderRepository.updateOrderStatus(orderId)
    }
}
