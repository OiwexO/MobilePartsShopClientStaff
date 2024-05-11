package com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.device_type

import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.ResponseRequestMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.device_type.DeviceTypeRequestDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.device_type.DeviceTypeResponseDto
import com.iwex.mobilepartsshopstaff.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshopstaff.domain.entity.part.device_type.DeviceTypeRequest

class DeviceTypeMapper :
    ResponseRequestMapper<DeviceType, DeviceTypeRequest, DeviceTypeResponseDto, DeviceTypeRequestDto>() {

    override fun toRequestDto(request: DeviceTypeRequest): DeviceTypeRequestDto {
        return DeviceTypeRequestDto(
            nameEn = request.nameEn,
            nameUk = request.nameUk
        )
    }

    override fun toEntity(dto: DeviceTypeResponseDto): DeviceType {
        return DeviceType(
            id = dto.id,
            nameEn = dto.nameEn,
            nameUk = dto.nameUk
        )
    }
}