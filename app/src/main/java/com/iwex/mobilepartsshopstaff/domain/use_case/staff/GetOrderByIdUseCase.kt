package com.iwex.mobilepartsshopstaff.domain.use_case.staff

import com.iwex.mobilepartsshopstaff.domain.entity.order.Order
import com.iwex.mobilepartsshopstaff.domain.repository.staff.StaffRepository
import javax.inject.Inject

class GetOrderByIdUseCase @Inject constructor(
    private val staffRepository: StaffRepository
) {
    suspend operator fun invoke(orderId: Long): Result<Order> {
        return staffRepository.getOrderById(orderId)
    }
}