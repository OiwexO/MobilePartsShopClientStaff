package com.iwex.mobilepartsshopstaff.domain.use_case.part.device_type

import com.iwex.mobilepartsshopstaff.domain.entity.part.DeviceType
import com.iwex.mobilepartsshopstaff.domain.repository.part.DeviceTypeRepository
import javax.inject.Inject

class GetAllDeviceTypesUseCase @Inject constructor(
    private val deviceTypeRepository: DeviceTypeRepository
) {
    suspend operator fun invoke(): Result<List<DeviceType>> {
        return deviceTypeRepository.getAllDeviceTypes()
    }
}