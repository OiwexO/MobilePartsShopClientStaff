package com.iwex.mobilepartsshopstaff.domain.repository.staff

import com.iwex.mobilepartsshopstaff.domain.entity.order.Order

interface StaffRepository {

    suspend fun getAssignedOrdersByStaffId(staffId: Long): Result<List<Order>>

    suspend fun updateOrderStatus(orderId: Long): Result<Order>
}