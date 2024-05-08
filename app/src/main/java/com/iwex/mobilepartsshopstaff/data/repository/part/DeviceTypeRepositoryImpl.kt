package com.iwex.mobilepartsshopstaff.data.repository.part

import com.iwex.mobilepartsshopstaff.data.remote.MainApiService
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.device_type.DeviceTypeMapper
import com.iwex.mobilepartsshopstaff.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshopstaff.domain.entity.part.device_type.DeviceTypeRequest
import com.iwex.mobilepartsshopstaff.domain.repository.part.DeviceTypeRepository
import javax.inject.Inject

class DeviceTypeRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val mapper: DeviceTypeMapper,
) : DeviceTypeRepository {

    override suspend fun getAllDeviceTypes(): Result<List<DeviceType>> {
        val response = try {
            apiService.getAllDeviceTypes()
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entities = mapper.toEntityList(response)
        return Result.success(entities)
    }

    override suspend fun getDeviceType(id: Long): Result<DeviceType> {
        TODO("Not yet implemented")
    }

    override suspend fun createDeviceType(deviceTypeRequest: DeviceTypeRequest): Result<DeviceType> {
        TODO("Not yet implemented")
    }

    override suspend fun updateDeviceType(
        id: Long,
        deviceTypeRequest: DeviceTypeRequest
    ): Result<DeviceType> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDeviceType(id: Long): Result<Unit> {
        TODO("Not yet implemented")
    }
}