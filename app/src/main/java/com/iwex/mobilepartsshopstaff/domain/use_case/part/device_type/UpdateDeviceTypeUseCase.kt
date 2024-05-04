package com.iwex.mobilepartsshopstaff.domain.use_case.part.device_type

import com.iwex.mobilepartsshopstaff.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshopstaff.domain.entity.part.device_type.DeviceTypeRequest
import com.iwex.mobilepartsshopstaff.domain.repository.part.DeviceTypeRepository
import javax.inject.Inject

class UpdateDeviceTypeUseCase @Inject constructor(
    private val deviceTypeRepository: DeviceTypeRepository
) {
    suspend operator fun invoke(
        id: Long,
        deviceTypeRequest: DeviceTypeRequest
    ): Result<DeviceType> {
        return deviceTypeRepository.updateDeviceType(id, deviceTypeRequest)
    }
}