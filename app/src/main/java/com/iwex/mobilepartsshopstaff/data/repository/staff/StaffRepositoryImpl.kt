package com.iwex.mobilepartsshopstaff.data.repository.staff

import com.iwex.mobilepartsshopstaff.data.remote.MainApiService
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.order.OrderMapper
import com.iwex.mobilepartsshopstaff.domain.entity.order.Order
import com.iwex.mobilepartsshopstaff.domain.repository.staff.StaffRepository
import javax.inject.Inject

class StaffRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val mapper: OrderMapper
) : StaffRepository {

    override suspend fun getAssignedOrdersByStaffId(staffId: Long): Result<List<Order>> {
        val response = try {
            apiService.getAssignedOrders(staffId)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entities = mapper.toEntityList(response)
        return Result.success(entities)
    }

    override suspend fun getOrderById(orderId: Long): Result<Order> {
        val response = try {
            apiService.getOrderById(orderId)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun updateOrderStatus(orderId: Long): Result<Order> {
        val response = try {
            apiService.updateOrderStatus(orderId)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }
}