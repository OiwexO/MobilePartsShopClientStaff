package com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.part_type

import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.ResponseRequestMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.part_type.PartTypeRequestDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.part_type.PartTypeResponseDto
import com.iwex.mobilepartsshopstaff.domain.entity.part.part_type.PartType
import com.iwex.mobilepartsshopstaff.domain.entity.part.part_type.PartTypeRequest

class PartTypeMapper :
    ResponseRequestMapper<PartType, PartTypeRequest, PartTypeResponseDto, PartTypeRequestDto>() {

    override fun toRequestDto(request: PartTypeRequest): PartTypeRequestDto {
        return PartTypeRequestDto(
            nameEn = request.nameEn,
            nameUk = request.nameUk
        )
    }

    override fun toEntity(dto: PartTypeResponseDto): PartType {
        return PartType(
            id = dto.id,
            nameEn = dto.nameEn,
            nameUk = dto.nameUk
        )
    }
}