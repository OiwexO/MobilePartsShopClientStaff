package com.iwex.mobilepartsshopstaff.domain.repository.part

import com.iwex.mobilepartsshopstaff.domain.entity.part.DeviceType
import com.iwex.mobilepartsshopstaff.domain.entity.part.DeviceTypeRequest

interface DeviceTypeRepository {

    suspend fun getAllDeviceTypes(): Result<List<DeviceType>>

    suspend fun getDeviceType(id: Long): Result<DeviceType>

    suspend fun createDeviceType(deviceTypeRequest: DeviceTypeRequest): Result<DeviceType>

    suspend fun updateDeviceType(id: Long, deviceTypeRequest: DeviceTypeRequest): Result<DeviceType>

    suspend fun deleteDeviceType(id: Long): Result<Unit>
}
