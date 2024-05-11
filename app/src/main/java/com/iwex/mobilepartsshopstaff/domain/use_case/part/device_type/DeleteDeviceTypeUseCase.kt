package com.iwex.mobilepartsshopstaff.domain.use_case.part.device_type

import com.iwex.mobilepartsshopstaff.domain.repository.part.DeviceTypeRepository
import javax.inject.Inject

class DeleteDeviceTypeUseCase @Inject constructor(
    private val deviceTypeRepository: DeviceTypeRepository
) {
    suspend operator fun invoke(id: Long): Result<Unit> {
        return deviceTypeRepository.deleteDeviceType(id)
    }
}
