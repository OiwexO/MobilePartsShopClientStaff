package com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.manufacturer

import com.iwex.mobilepartsshopstaff.data.remote.ApiUtils
import com.iwex.mobilepartsshopstaff.data.remote.ApiUtils.Companion.manufacturerLogoToMultiPartBody
import com.iwex.mobilepartsshopstaff.data.remote.ApiUtils.Companion.stringToRequestBody
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.ResponseRequestMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.manufacturer.ManufacturerRequestDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.manufacturer.ManufacturerResponseDto
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.ManufacturerRequest

class ManufacturerMapper :
    ResponseRequestMapper<Manufacturer, ManufacturerRequest, ManufacturerResponseDto, ManufacturerRequestDto>() {

    override fun toRequestDto(request: ManufacturerRequest): ManufacturerRequestDto {
        val namePart = stringToRequestBody(request.name)
        return ManufacturerRequestDto(
            name = namePart,
            logo = manufacturerLogoToMultiPartBody(request.logoImage)
        )
    }

    override fun toEntity(dto: ManufacturerResponseDto): Manufacturer {
        return Manufacturer(
            id = dto.id,
            name = dto.name,
            logoUrl = ApiUtils.getManufacturerLogoUrl(dto.id)
        )
    }
}