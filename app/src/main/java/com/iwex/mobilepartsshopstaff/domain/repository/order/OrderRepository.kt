package com.iwex.mobilepartsshopstaff.domain.repository.order

import com.iwex.mobilepartsshopstaff.domain.entity.order.Order

interface OrderRepository {

    suspend fun getAssignedOrdersByStaffId(staffId: Long): Result<List<Order>>

    suspend fun updateOrderStatus(orderId: Long): Result<Order>
}