package com.iwex.mobilepartsshopstaff.domain.use_case.order

import com.iwex.mobilepartsshopstaff.domain.entity.order.Order
import com.iwex.mobilepartsshopstaff.domain.repository.order.OrderRepository
import javax.inject.Inject

class GetAssignedOrdersByStaffIdUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(staffId: Long): Result<List<Order>> {
        return orderRepository.getAssignedOrdersByStaffId(staffId)
    }
}