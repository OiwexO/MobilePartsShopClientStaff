package com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part

import com.iwex.mobilepartsshopstaff.data.remote.ApiUtils
import com.iwex.mobilepartsshopstaff.data.remote.ApiUtils.Companion.doubleToRequestBody
import com.iwex.mobilepartsshopstaff.data.remote.ApiUtils.Companion.intToRequestBody
import com.iwex.mobilepartsshopstaff.data.remote.ApiUtils.Companion.longToRequestBody
import com.iwex.mobilepartsshopstaff.data.remote.ApiUtils.Companion.partImageToMultipartBody
import com.iwex.mobilepartsshopstaff.data.remote.ApiUtils.Companion.stringToRequestBody
import com.iwex.mobilepartsshopstaff.data.remote.ApiUtils.Companion.stringsToRequestBody
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.ResponseRequestMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.device_type.DeviceTypeMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.manufacturer.ManufacturerMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.part_type.PartTypeMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.PartRequestDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.part.PartResponseDto
import com.iwex.mobilepartsshopstaff.domain.entity.part.Part
import com.iwex.mobilepartsshopstaff.domain.entity.part.PartRequest
import javax.inject.Inject

class PartMapper @Inject constructor(
    private val manufacturerMapper: ManufacturerMapper,
    private val deviceTypeMapper: DeviceTypeMapper,
    private val partTypeMapper: PartTypeMapper,
) : ResponseRequestMapper<Part, PartRequest, PartResponseDto, PartRequestDto>() {

    override fun toRequestDto(request: PartRequest): PartRequestDto {
        return PartRequestDto(
            price = doubleToRequestBody(request.price),
            quantity = intToRequestBody(request.quantity),
            name = stringToRequestBody(request.name),
            deviceModels = stringsToRequestBody(request.deviceModels),
            specifications = stringToRequestBody(request.specifications),
            manufacturerId = longToRequestBody(request.manufacturerId),
            deviceTypeId = longToRequestBody(request.deviceTypeId),
            partTypeId = longToRequestBody(request.partTypeId),
            partImage = partImageToMultipartBody(request.image)
        )
    }

    override fun toEntity(dto: PartResponseDto): Part {
        val manufacturer = manufacturerMapper.toEntity(dto.manufacturer)
        val deviceType = deviceTypeMapper.toEntity(dto.deviceType)
        val partType = partTypeMapper.toEntity(dto.partType)
        return Part(
            id = dto.id,
            price = dto.price,
            quantity = dto.quantity,
            name = dto.name,
            deviceModels = dto.deviceModels,
            specifications = dto.specifications,
            manufacturer = manufacturer,
            deviceType = deviceType,
            partType = partType,
            imageUrl = ApiUtils.getPartImageUrl(dto.id)
        )
    }
}